package com.renovavision.videosearch.video_detail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.renovavision.videosearch.App;
import com.vyng.videosearch.R;
import com.renovavision.videosearch.domain.model.VideoItem;
import com.renovavision.videosearch.mvp.BaseMvpView;
import com.renovavision.videosearch.mvp.BundleBuilder;
import com.renovavision.videosearch.mvp.MvpPresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class VideoDetailView extends BaseMvpView implements VideoDetailContract.View {

    private static final String KEY_ITEM = "KEY_ITEM";

    @Inject
    protected VideoDetailPresenter videoDetailPresenter;

    @BindView(R.id.exo_player_view)
    SimpleExoPlayerView simpleExoPlayerView;

    private VideoItem videoItem;

    private SimpleExoPlayer player;

    public VideoDetailView(VideoItem videoItem) {
        this(new BundleBuilder(new Bundle())
                .putSerializable(KEY_ITEM, videoItem)
                .build());
    }

    public VideoDetailView(Bundle args) {
        super(args);
    }

    @Override
    protected void injectDependencies() {
        DaggerVideoDetailComponent.builder()
                .applicationComponent(App.get().getApplicationComponent())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.view_video_detail;
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        initPlayer();

        videoItem = (VideoItem) getArgs().getSerializable(KEY_ITEM);
        videoDetailPresenter.init(videoItem);
    }

    private void initPlayer() {
        boolean needNewPlayer = player == null;
        if (needNewPlayer) {
            simpleExoPlayerView.requestFocus();

            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            DefaultTrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

            player = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
            simpleExoPlayerView.setPlayer(player);
            player.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);
        releasePlayer();
    }

    @Override
    protected MvpPresenter getPresenter() {
        return videoDetailPresenter;
    }

    @Override
    public void setUp(String url) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(),
                Util.getUserAgent(getActivity(), "VideoSearch"));
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(url));
        player.prepare(videoSource);
    }
}

package com.renovavision.videosearch.video_detail;

import com.renovavision.videosearch.domain.model.VideoItem;
import com.renovavision.videosearch.mvp.BaseMvpPresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */

public class VideoDetailPresenter extends BaseMvpPresenter<VideoDetailView> implements VideoDetailContract.Presenter {

    private VideoItem videoItem;

    private CompositeDisposable compositeDisposable;

    @Inject
    public VideoDetailPresenter() {
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void init(VideoItem videoItem) {
        this.videoItem = videoItem;
        getMvpView().setUp(videoItem.getVideoUrl());
    }

    @Override
    public void detachView() {
        super.detachView();
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}

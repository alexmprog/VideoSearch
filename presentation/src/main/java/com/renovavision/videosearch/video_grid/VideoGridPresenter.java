package com.renovavision.videosearch.video_grid;

import android.text.TextUtils;

import com.renovavision.videosearch.domain.model.QueryParams;
import com.renovavision.videosearch.domain.model.VideoItem;
import com.renovavision.videosearch.domain.usecase.GetVideoItemsUseCase;
import com.renovavision.videosearch.mvp.BaseMvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class VideoGridPresenter extends BaseMvpPresenter<VideoGridView> implements VideoGridContract.Presenter {

    private QueryParams queryParams;

    private GetVideoItemsUseCase getVideoItemsUseCase;

    private Disposable getVideoItemsDisposable;

    private List<VideoItem> videoItemList;

    @Inject
    public VideoGridPresenter(GetVideoItemsUseCase getVideoItemsUseCase) {
        this.getVideoItemsUseCase = getVideoItemsUseCase;
        this.videoItemList = new ArrayList<>();
        this.queryParams = new QueryParams();
        queryParams.setLimit(25);
    }

    @Override
    public void attachView(VideoGridView mvpView) {
        super.attachView(mvpView);
        if (videoItemList != null && !videoItemList.isEmpty()) {
            queryParams.setOffset(videoItemList.size());
            getMvpView().onLoading(false);
            getMvpView().onDataLoaded(videoItemList, true, true);
        } else {
            loadData();
        }
    }

    @Override
    public void load(String query) {
        String paramsQuery = queryParams.getQuery();
        if (!TextUtils.equals(paramsQuery, query)) {
            queryParams.setQuery(query);
            queryParams.setOffset(0);
            loadData();
        }
    }

    @Override
    public void loadMore() {
        loadData();
    }

    private void loadData() {
        if (getVideoItemsDisposable != null) {
            getVideoItemsDisposable.dispose();
        }

        getMvpView().onLoading(true);
        getVideoItemsDisposable = getVideoItemsUseCase.getVideoItems(queryParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(videoItems -> {
                    if (isViewAttached()) {
                        getMvpView().onLoading(false);
                        boolean isInput = queryParams.getOffset() == 0;
                        if (isInput) {
                            videoItemList = new ArrayList<>();
                        }
                        videoItemList.addAll(videoItems);
                        getMvpView().onDataLoaded(videoItems, isInput, queryParams.getLimit() == videoItems.size());
                        queryParams.setOffset(queryParams.getOffset() + videoItems.size());
                    }
                }, throwable -> {
                    Timber.e(throwable);
                    if (isViewAttached()) {
                        getMvpView().onLoading(false);
                        getMvpView().onError(throwable.getMessage());
                    }
                });
    }

    @Override
    public void detachView() {
        if (getVideoItemsDisposable != null) {
            getVideoItemsDisposable.dispose();
        }
        super.detachView();
    }
}

package com.renovavision.videosearch.video_grid;

import android.support.annotation.NonNull;

import com.renovavision.videosearch.domain.model.VideoItem;

import java.util.List;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */

public interface VideoGridContract {

    interface View {

        void onDataLoaded(@NonNull List<VideoItem> models, boolean isInput, boolean needLoadMore);

        void onLoading(boolean isLoading);

        void onError(String message);
    }

    interface Presenter {

        void load(String query);

        void loadMore();
    }
}

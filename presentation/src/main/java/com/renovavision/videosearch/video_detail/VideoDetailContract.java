package com.renovavision.videosearch.video_detail;

import com.renovavision.videosearch.domain.model.VideoItem;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public interface VideoDetailContract {

    interface View {

        void setUp(String url);
    }

    interface Presenter {

        void init(VideoItem videoItem);
    }
}

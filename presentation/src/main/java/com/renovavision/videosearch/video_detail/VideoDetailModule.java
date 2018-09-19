package com.renovavision.videosearch.video_detail;

import com.renovavision.videosearch.di.ScreenScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */

@Module
public class VideoDetailModule {

    @ScreenScope
    @Provides
    public VideoDetailPresenter provideVideoDetailPresenter() {
        return new VideoDetailPresenter();
    }
}

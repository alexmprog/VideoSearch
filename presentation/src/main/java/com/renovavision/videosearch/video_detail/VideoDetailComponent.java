package com.renovavision.videosearch.video_detail;

import com.renovavision.videosearch.di.ApplicationComponent;
import com.renovavision.videosearch.di.ScreenScope;

import dagger.Component;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
@ScreenScope
@Component(modules = {VideoDetailModule.class},
        dependencies = {ApplicationComponent.class})
public interface VideoDetailComponent {

    void inject(VideoDetailView videoDetailView);
}

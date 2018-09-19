package com.renovavision.videosearch.video_grid;

import com.renovavision.videosearch.di.ApplicationComponent;
import com.renovavision.videosearch.di.ScreenScope;

import dagger.Component;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */

@ScreenScope
@Component(modules = {VideoGridModule.class},
        dependencies = {ApplicationComponent.class})
public interface VideoGridComponent {

    void inject(VideoGridView videoGridView);
}

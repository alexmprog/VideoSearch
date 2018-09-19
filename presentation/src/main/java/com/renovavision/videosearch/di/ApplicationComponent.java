package com.renovavision.videosearch.di;

import android.app.Application;

import com.renovavision.videosearch.domain.repository.VideoItemRepository;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }

    VideoItemRepository videoItemRepository();
}

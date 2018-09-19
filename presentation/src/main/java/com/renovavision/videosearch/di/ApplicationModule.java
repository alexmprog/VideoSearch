package com.renovavision.videosearch.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
@Module(includes = {ApiModule.class, DataModule.class})
public class ApplicationModule {

    @Provides
    @Singleton
    public Context provideApplicationContext(Application application) {
        return application.getApplicationContext();
    }
}

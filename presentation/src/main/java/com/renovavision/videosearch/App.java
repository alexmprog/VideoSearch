package com.renovavision.videosearch;

import android.app.Application;

import com.renovavision.videosearch.di.ApplicationComponent;
import com.renovavision.videosearch.di.DaggerApplicationComponent;

import timber.log.Timber;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class App extends Application {

    private static App sInstance;

    public static App get() {
        return sInstance;
    }

    private ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        applicationComponent = DaggerApplicationComponent.builder()
                .application(this)
                .build();

        // setup timber logging only for now
        Timber.plant(new Timber.DebugTree());
    }
}

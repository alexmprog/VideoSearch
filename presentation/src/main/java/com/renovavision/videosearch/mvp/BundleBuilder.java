package com.renovavision.videosearch.mvp;

import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class BundleBuilder {

    private final Bundle bundle;

    public BundleBuilder(Bundle bundle) {
        this.bundle = bundle;
    }

    public BundleBuilder putSerializable(String key, Serializable value) {
        bundle.putSerializable(key, value);
        return this;
    }

    public Bundle build() {
        return bundle;
    }
}
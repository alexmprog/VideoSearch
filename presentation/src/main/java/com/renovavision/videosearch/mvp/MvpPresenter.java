package com.renovavision.videosearch.mvp;

import android.os.Bundle;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();

    void saveState(Bundle outBundle);

    void restoreState(Bundle savedInstanceState);
}

package com.renovavision.videosearch.mvp;

import android.os.Bundle;

import java.lang.ref.WeakReference;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public abstract class BaseMvpPresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> mvpView;

    @Override
    public void attachView(V mvpView) {
        this.mvpView = new WeakReference<>(mvpView);
    }

    @Override
    public void detachView() {
        if (mvpView != null) {
            mvpView.clear();
        }
    }

    protected boolean isViewAttached() {
        return mvpView != null && mvpView.get() != null;
    }

    public V getMvpView() {
        if (mvpView != null) {
            return mvpView.get();
        }
        return null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

    @Override
    public void restoreState(Bundle savedInstanceState) {
        // do nothing here - will implement in children classes
    }

    @Override
    public void saveState(Bundle outBundle) {
        // do nothing here - will implement in children classes
    }
}

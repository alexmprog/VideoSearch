package com.renovavision.videosearch.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public abstract class BaseMvpView extends Controller implements MvpView {

    private Unbinder unbinder;

    protected BaseMvpView() { }

    public BaseMvpView(Bundle args) {
        super(args);
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void onContextAvailable(@NonNull Context context) {
        super.onContextAvailable(context);
        injectDependencies();
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        getPresenter().attachView(this);
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);
        getPresenter().detachView();
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        unbinder.unbind();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenter().saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getPresenter().restoreState(savedInstanceState);
    }

    protected abstract void injectDependencies();

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract MvpPresenter getPresenter();
}

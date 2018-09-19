package com.renovavision.videosearch.video_grid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.renovavision.videosearch.App;
import com.renovavision.videosearch.recycler.EndlessPagerAdapter;
import com.vyng.videosearch.R;
import com.renovavision.videosearch.domain.model.VideoItem;
import com.renovavision.videosearch.mvp.BaseMvpView;
import com.renovavision.videosearch.mvp.MvpPresenter;
import com.renovavision.videosearch.video_detail.VideoDetailView;
import com.renovavision.videosearch.recycler.RectangleItemDecoration;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class VideoGridView extends BaseMvpView implements VideoGridContract.View, VideoGridAdapter.OnItemClickListener, EndlessPagerAdapter.OnLoadMoreListener {

    private static final int GRID_COLUMNS_COUNT = 3;

    @Inject
    protected VideoGridPresenter videoGridPresenter;

    @BindView(R.id.edit_text_view)
    protected AppCompatEditText searchView;

    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private Disposable searchViewRxDisposable;

    private VideoGridAdapter videoGridAdapter;

    public VideoGridView() {
    }

    @Override
    protected void injectDependencies() {
        DaggerVideoGridComponent.builder()
                .applicationComponent(App.get().getApplicationComponent())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.view_video_grid;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return videoGridPresenter;
    }

    @Override
    protected void onAttach(@NonNull View view) {
        initRecyclerView();
        hideKeyboard();

        super.onAttach(view);

        subscribeToSearchViewTextChanges();
    }

    private void subscribeToSearchViewTextChanges() {
        searchViewRxDisposable = RxTextView.textChanges(searchView)
                .debounce(400, TimeUnit.MILLISECONDS)
                .skip(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(charSequence -> videoGridPresenter.load(charSequence.toString()), Timber::e);
    }


    private void initRecyclerView() {
        Activity activity = getActivity();

        int paddingSize = activity.getResources().getDimensionPixelSize(R.dimen.grid_item_space);
        recyclerView.setPadding(paddingSize, paddingSize, paddingSize, paddingSize);
        recyclerView.addItemDecoration(new RectangleItemDecoration(paddingSize));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // calculate size for item
        int imageSize = calculateGridItemSize(activity);

        recyclerView.setLayoutManager(new GridLayoutManager(activity, GRID_COLUMNS_COUNT));
        videoGridAdapter = new VideoGridAdapter(imageSize);
        videoGridAdapter.setOnItemClickListener(this);
        videoGridAdapter.setLoadMoreListener(this);
        recyclerView.setAdapter(videoGridAdapter);
    }

    protected int calculateGridItemSize(@NonNull Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        Point p = new Point();
        defaultDisplay.getSize(p);
        int itemMargin = context.getResources().getDimensionPixelSize(R.dimen.grid_item_space);
        return (p.x - 4 * itemMargin) / GRID_COLUMNS_COUNT;
    }

    @Override
    public void onDataLoaded(@NonNull List<VideoItem> models, boolean isInput, boolean needLoadMore) {
        videoGridAdapter.setLoaded(needLoadMore);
        if (isInput) {
            videoGridAdapter.setInput(models);
        } else {
            videoGridAdapter.loadedMore(models);
        }
    }

    @Override
    public void onLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClicked(int position) {
        VideoItem itemByPosition = videoGridAdapter.getItemByPosition(position);
        getRouter().pushController(RouterTransaction.with(new VideoDetailView(itemByPosition))
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));
    }

    @Override
    public void onLoadMore() {
        videoGridPresenter.loadMore();
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);
        if (searchViewRxDisposable != null) {
            searchViewRxDisposable.dispose();
        }
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
    }
}

package com.renovavision.videosearch.recycler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public abstract class EndlessPagerAdapter<M, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    @NonNull
    protected List<M> models;

    protected boolean loadingInProgress = false;
    protected boolean needLoadMore = false;
    protected OnLoadMoreListener loadMoreListener;

    public EndlessPagerAdapter() {
        this(null);
    }

    public EndlessPagerAdapter(@Nullable List<M> models) {
        this.models = models != null ? models : new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (needLoadMore && !loadingInProgress && getItemCount() - 1 == position && loadMoreListener != null) {
            loadMoreListener.onLoadMore();
            loadingInProgress = true;
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public M getItemByPosition(int position) {
        if (position >= models.size()) {
            return null;
        }
        return models.get(position);
    }

    public int getPositionByItem(@NonNull M model) {
        return models.indexOf(model);
    }

    public void setLoaded(boolean needLoadMore) {
        this.loadingInProgress = false;
        this.needLoadMore = needLoadMore;
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}

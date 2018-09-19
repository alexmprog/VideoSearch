package com.renovavision.videosearch.recycler;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class RectangleItemDecoration extends RecyclerView.ItemDecoration {

    private final int left;
    private final int right;
    private final int top;
    private final int bottom;

    public RectangleItemDecoration(int space) {
        left = space;
        right = space;
        top = space;
        bottom = space;
    }

    public RectangleItemDecoration(int left, int top, int right, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = left;
        outRect.right = right;
        outRect.top = top;
        outRect.bottom = bottom;
    }
}
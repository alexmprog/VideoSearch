package com.renovavision.videosearch.data.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class PageData {

    @SerializedName("total_count")
    private int totalCount;

    private int count;

    private int offset;

    public PageData() {
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getCount() {
        return count;
    }

    public int getOffset() {
        return offset;
    }
}

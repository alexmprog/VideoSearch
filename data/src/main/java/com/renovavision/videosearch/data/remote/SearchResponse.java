package com.renovavision.videosearch.data.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class SearchResponse {

    private List<GifData> data;

    @SerializedName("pagination")
    private PageData pageData;

    public SearchResponse() {
    }

    public List<GifData> getData() {
        return data;
    }

    public PageData getPageData() {
        return pageData;
    }
}

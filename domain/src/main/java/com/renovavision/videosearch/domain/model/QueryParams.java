package com.renovavision.videosearch.domain.model;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */

public class QueryParams {

    private String query;

    private int limit;

    private int offset;

    public QueryParams() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}

package com.renovavision.videosearch.data.api;

import com.renovavision.videosearch.data.remote.SearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public interface GiphySearchApiService {

    @GET("v1/gifs/search")
    Single<SearchResponse> searchItems(@Query("q") String query, @Query("limit") int limit, @Query("offset") int offset);
}

package com.renovavision.videosearch.domain.repository.datasource;

import com.renovavision.videosearch.domain.model.QueryParams;
import com.renovavision.videosearch.domain.model.VideoItem;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public interface VideoItemDataSource {

    Single<List<VideoItem>> getVideoItems(@NonNull QueryParams queryParams);
}

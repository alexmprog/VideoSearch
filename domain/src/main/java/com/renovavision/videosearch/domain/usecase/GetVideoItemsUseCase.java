package com.renovavision.videosearch.domain.usecase;

import com.renovavision.videosearch.domain.model.QueryParams;
import com.renovavision.videosearch.domain.model.VideoItem;
import com.renovavision.videosearch.domain.repository.VideoItemRepository;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class GetVideoItemsUseCase {

    private VideoItemRepository videoItemRepository;

    public GetVideoItemsUseCase(VideoItemRepository videoItemRepository) {
        this.videoItemRepository = videoItemRepository;
    }

    public Single<List<VideoItem>> getVideoItems(@NonNull QueryParams queryParams) {
        return isValidParams(queryParams).flatMap(isValid -> videoItemRepository.getVideoItems(queryParams));
    }

    private Single<Boolean> isValidParams(@NonNull QueryParams queryParams) {
        if (queryParams.getLimit() > 0) {
            return Single.just(true);
        } else {
            return Single.error(new IllegalStateException("Check your query params"));
        }
    }
}

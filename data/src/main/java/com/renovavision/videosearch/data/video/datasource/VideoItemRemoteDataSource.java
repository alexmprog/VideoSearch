package com.renovavision.videosearch.data.video.datasource;

import com.renovavision.videosearch.data.api.GiphySearchApiService;
import com.renovavision.videosearch.domain.model.QueryParams;
import com.renovavision.videosearch.domain.model.VideoItem;
import com.renovavision.videosearch.domain.repository.datasource.VideoItemDataSource;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class VideoItemRemoteDataSource implements VideoItemDataSource {

    private final GiphySearchApiService giphySearchApiService;

    private final SearchResponseModelMapper searchResponseModelMapper;

    public VideoItemRemoteDataSource(GiphySearchApiService giphySearchApiService, SearchResponseModelMapper searchResponseModelMapper) {
        this.giphySearchApiService = giphySearchApiService;
        this.searchResponseModelMapper = searchResponseModelMapper;
    }

    @Override
    public Single<List<VideoItem>> getVideoItems(QueryParams queryParams) {
        return giphySearchApiService.searchItems(queryParams.getQuery(), queryParams.getLimit(),
                queryParams.getOffset()).map(searchResponseModelMapper::fromEntity);
    }
}

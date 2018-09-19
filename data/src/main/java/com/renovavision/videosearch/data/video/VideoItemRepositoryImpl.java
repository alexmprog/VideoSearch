package com.renovavision.videosearch.data.video;

import com.renovavision.videosearch.domain.model.QueryParams;
import com.renovavision.videosearch.domain.model.VideoItem;
import com.renovavision.videosearch.domain.repository.VideoItemRepository;
import com.renovavision.videosearch.domain.repository.datasource.VideoItemDataSource;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class VideoItemRepositoryImpl implements VideoItemRepository {

    private final VideoItemDataSource remoteDataSource;

    public VideoItemRepositoryImpl(VideoItemDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Single<List<VideoItem>> getVideoItems(QueryParams queryParams) {
        return remoteDataSource.getVideoItems(queryParams);
    }
}

package com.renovavision.videosearch.di;

import com.renovavision.videosearch.data.api.GiphySearchApiService;
import com.renovavision.videosearch.data.video.VideoItemRepositoryImpl;
import com.renovavision.videosearch.data.video.datasource.SearchResponseModelMapper;
import com.renovavision.videosearch.data.video.datasource.VideoItemRemoteDataSource;
import com.renovavision.videosearch.domain.repository.VideoItemRepository;
import com.renovavision.videosearch.domain.repository.datasource.VideoItemDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */

@Module
public class DataModule {

    @Singleton
    @Provides
    public SearchResponseModelMapper provideSearchResponseModelMapper() {
        return new SearchResponseModelMapper();
    }

    @Singleton
    @Provides
    public VideoItemDataSource provideVideoItemRemoteDataSource(GiphySearchApiService giphySearchApiService,
                                                                SearchResponseModelMapper searchResponseModelMapper) {
        return new VideoItemRemoteDataSource(giphySearchApiService, searchResponseModelMapper);
    }

    @Singleton
    @Provides
    public VideoItemRepository provideVideoItemRepository(VideoItemDataSource videoItemDataSource) {
        return new VideoItemRepositoryImpl(videoItemDataSource);
    }
}

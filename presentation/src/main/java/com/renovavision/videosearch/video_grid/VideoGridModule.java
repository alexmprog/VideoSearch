package com.renovavision.videosearch.video_grid;

import com.renovavision.videosearch.di.ScreenScope;
import com.renovavision.videosearch.domain.repository.VideoItemRepository;
import com.renovavision.videosearch.domain.usecase.GetVideoItemsUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */

@Module
public class VideoGridModule {

    @Provides
    @ScreenScope
    public GetVideoItemsUseCase provideGetVideoItemsUseCase(VideoItemRepository videoItemRepository) {
        return new GetVideoItemsUseCase(videoItemRepository);
    }

    @Provides
    @ScreenScope
    public VideoGridPresenter provideVideoGridPresenter(GetVideoItemsUseCase getVideoItemsUseCase) {
        return new VideoGridPresenter(getVideoItemsUseCase);
    }
}

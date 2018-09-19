package com.renovavision.videosearch.data.video.datasource;

import android.text.TextUtils;

import com.renovavision.videosearch.data.remote.GifData;
import com.renovavision.videosearch.data.remote.SearchResponse;
import com.renovavision.videosearch.domain.model.ModelMapper;
import com.renovavision.videosearch.domain.model.VideoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class SearchResponseModelMapper implements ModelMapper<SearchResponse, List<VideoItem>> {

    @Override
    public List<VideoItem> fromEntity(SearchResponse entity) {
        List<VideoItem> videoItems = new ArrayList<>();
        List<GifData> data = entity.getData();
        if (data == null || data.isEmpty()) {
            return videoItems;
        }

        for (GifData gifData : data) {
            String id = gifData.getId();
            if (TextUtils.isEmpty(id)) {
                continue;
            }
            VideoItem videoItem = new VideoItem();
            videoItem.setId(id);
            videoItem.setUserName(gifData.getUserName());
            videoItem.setPreviewUrl(gifData.getPreviewImageUrl());
            videoItem.setVideoUrl(gifData.getVideoUrl());
            videoItems.add(videoItem);
        }

        return videoItems;
    }

    @Override
    public SearchResponse toEntity(List<VideoItem> model) {
        return new SearchResponse();
    }
}

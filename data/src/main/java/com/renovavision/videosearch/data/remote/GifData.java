package com.renovavision.videosearch.data.remote;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class GifData {

    private String id;

    @SerializedName("username")
    private String userName;

    // TODO: need optimize JSON parsing here - use MAP only for testing
    private Map<String, Map<String, String>> images;

    public GifData() {
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPreviewImageUrl() {
        Map<String, String> previewImage = images.get("fixed_height_still");
        if (previewImage == null) {
            previewImage = images.get("fixed_height");
        }
        if (previewImage != null) {
            return previewImage.get("url");
        }
        return null;
    }

    public String getVideoUrl() {
        Map<String, String> sourceImage = images.get("original");
        if (sourceImage != null) {
            return sourceImage.get("mp4");
        }
        return null;
    }
}

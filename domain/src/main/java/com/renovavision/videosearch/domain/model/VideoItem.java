package com.renovavision.videosearch.domain.model;

import java.io.Serializable;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public class VideoItem implements Serializable{

    private String id;

    private String previewUrl;

    private String videoUrl;

    private String userName;

    public VideoItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

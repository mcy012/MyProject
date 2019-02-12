package com.example.eda1707.movie2.data;

import java.io.Serializable;

public class PhotoInfoDto implements Serializable {
    private String imgUrl;
    private String videoUrl = "";
    private PhotoType type;

    public PhotoInfoDto(String photoUrl, PhotoType type) {
        this.imgUrl = photoUrl;
        this.type = type;
    }

    public PhotoInfoDto(String imgUrl, String videoUrl, PhotoType type) {
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public PhotoType getType() {
        return type;
    }

    public void setType(PhotoType type) {
        this.type = type;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "PhotoInfoDto{" +
                "imgUrl='" + imgUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", type=" + type +
                '}';
    }
}

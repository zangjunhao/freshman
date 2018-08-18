package com.mredrock.cyxbs.freshman.model.convert;

import java.util.List;

public class JunXun {
    private List<VideoBean> video;
    private List<PictureBean> picture;
    public List<VideoBean> getVideo() {
        return video;
    }

    public void setVideo(List<VideoBean> video) {
        this.video = video;
    }

    public List<PictureBean> getPicture() {
        return picture;
    }

    public void setPicture(List<PictureBean> picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
       return "video size"+ video.size();
    }
}

package com.mredrock.cyxbs.freshman.model.convert;

public class VideoBean {
    /**
     * name : 2016重庆邮电大学军训视频2
     * url : https://v.qq.com/x/page/r07539i9p1d.html?
     * video_pic : {"name":"UUID131","url":"/picture/38f98e9a9e8d498ea522252f67382236.png"}
     */

    private String name;
    private String url;
    private VideoPicBean video_pic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public VideoPicBean getVideo_pic() {
        return video_pic;
    }

    public void setVideo_pic(VideoPicBean video_pic) {
        this.video_pic = video_pic;
    }

}
package com.mredrock.cyxbs.freshman.model.convert;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class Strategy implements Comparable<Strategy>,Serializable{
    private int id;
    private String name;
    private String content;
    private List<String> picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    @Override
    public int compareTo(@NonNull Strategy strategy) {
        if (this.id>strategy.getId()){
            return 1;
        }else if (this.id<strategy.getId()){
            return -1;
        }else {
            return 0;
        }
    }
}

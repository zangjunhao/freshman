package com.mredrock.cyxbs.freshman.model.convert;

public class Describe_1 {

    /*
    *接口7
     */
        private int id;
        private String name;
        private String content;

    public Describe_1() {
    }

    public Describe_1(String name, String content) {
        this.name = name;
        this.content = content;
    }

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
}

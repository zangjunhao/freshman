package com.mredrock.cyxbs.freshman.model.convert;

public class Group_code implements Group<String>{
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    @Override
    public String getN() {
        return name;
    }

    @Override
    public String getContent() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

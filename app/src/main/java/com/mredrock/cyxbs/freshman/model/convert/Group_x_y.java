package com.mredrock.cyxbs.freshman.model.convert;

import java.util.List;

public class Group_x_y implements Group<List<Group_code>>{
    private String name;
    private List<Group_code> array1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Group_code> getArray1() {
        return array1;
    }

    public void setArray1(List<Group_code> array1) {
        this.array1 = array1;
    }

    @Override
    public String getN() {
        return name;
    }

    @Override
    public List<Group_code> getContent() {
        return array1;
    }
}

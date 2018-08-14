package com.mredrock.cyxbs.freshman.model.convert;

import java.util.List;

public class StudentRoom {
    private String name;
    private List<Strategy> array;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Strategy> getArray() {
        return array;
    }

    public void setArray(List<Strategy> array) {
        this.array = array;
    }
}

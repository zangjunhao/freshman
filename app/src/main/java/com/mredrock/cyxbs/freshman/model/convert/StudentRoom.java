package com.mredrock.cyxbs.freshman.model.convert;

import java.util.List;

public class StudentRoom {
    private String name;
    private List<Room> array;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getArray() {
        return array;
    }

    public void setArray(List<Room> array) {
        this.array = array;
    }
}

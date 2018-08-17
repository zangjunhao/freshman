package com.mredrock.cyxbs.freshman.model.convert;

import java.util.List;

public class HardSubject {
    private String name;
    private List<BelowSubject> array;

    public List<BelowSubject> getArray() {
        return array;
    }

    public void setArray(List<BelowSubject> array) {
        this.array = array;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

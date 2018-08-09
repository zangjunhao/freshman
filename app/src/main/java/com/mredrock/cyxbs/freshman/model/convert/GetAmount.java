package com.mredrock.cyxbs.freshman.model.convert;

import java.util.List;

public class GetAmount {

    /*
    *接口1
     */
    private int index;
    private int amount;
    private List<String> name;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}

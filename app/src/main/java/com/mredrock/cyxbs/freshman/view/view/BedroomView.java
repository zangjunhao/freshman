package com.mredrock.cyxbs.freshman.view.view;

import com.mredrock.cyxbs.freshman.model.convert.Strategy;

import java.util.List;

public interface BedroomView extends BaseView {
    void getBedroomName(List<String> list);
    void getData(Strategy strategy);
    void getDataFinish();
    void getNameFinish();
}

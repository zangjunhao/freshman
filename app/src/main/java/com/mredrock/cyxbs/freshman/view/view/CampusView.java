package com.mredrock.cyxbs.freshman.view.view;

import com.mredrock.cyxbs.freshman.model.convert.Strategy;

import java.util.List;

public interface CampusView extends BaseView {
    void getData(Strategy strategy);
    void onFinish();
}

package com.mredrock.cyxbs.freshman.view.view;

import com.mredrock.cyxbs.freshman.model.convert.BelowSubject;

import java.util.List;

public interface PatternView extends BaseView{
    void getData(List<BelowSubject> list);
    void onFinish();
    void getProportion(int men, int women);
    void getProportionFinish();
}

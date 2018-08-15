package com.mredrock.cyxbs.freshman.view.view;

import com.mredrock.cyxbs.freshman.model.convert.Group_x_y;

public interface OnlineView extends BaseView{
    void onGetData(Group_x_y group_x_y);
    void onFinish();
}

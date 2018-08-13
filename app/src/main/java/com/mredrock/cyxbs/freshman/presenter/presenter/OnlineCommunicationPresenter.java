package com.mredrock.cyxbs.freshman.presenter.presenter;

import android.content.Context;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.model.convert.Group_x_y;
import com.mredrock.cyxbs.freshman.model.convert.OnlineData;
import com.mredrock.cyxbs.freshman.model.http.httpmethods.HttpMethods;
import com.mredrock.cyxbs.freshman.presenter.base.BasePresenter;
import com.mredrock.cyxbs.freshman.view.view.OnlineView;

import java.util.List;

import rx.Subscriber;

public class OnlineCommunicationPresenter extends BasePresenter {

    private Context mContext;
    private OnlineView view;

    public OnlineCommunicationPresenter(Context mContext, OnlineView view) {
        this.mContext = mContext;
        this.view = view;
        this.mContext = mContext;
        attachView(view,mContext);
    }

    public void getData(String index,String key){
        Subscriber subscriber = new Subscriber<List<Group_x_y>>() {
            @Override
            public void onCompleted() {
                view.onFinish();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            @Override
            public void onNext(List<Group_x_y> list) {
                for (int i = 0, size = list.size();i<size;i++){
                    view.onGetData(list.get(i));
                }
            }
        };
        HttpMethods.getInstance().getServiceOfOnlineKey(subscriber,index,key);
    }
}

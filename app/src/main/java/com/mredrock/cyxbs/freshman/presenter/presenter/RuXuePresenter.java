package com.mredrock.cyxbs.freshman.presenter.presenter;

import android.content.Context;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.model.convert.Describe_1;
import com.mredrock.cyxbs.freshman.model.http.httpmethods.DescribeMethod;
import com.mredrock.cyxbs.freshman.presenter.base.BasePresenter;
import com.mredrock.cyxbs.freshman.view.view.BaseView;
import com.mredrock.cyxbs.freshman.view.view.RuXueView;

import java.util.List;

import rx.Subscriber;

public class RuXuePresenter extends BasePresenter<BaseView>{
    private RuXueView view;
    private Context mContext;

    public RuXuePresenter(RuXueView view, Context mContext) {
        attachView(view,mContext);
        this.view = view;
        this.mContext = mContext;
        getData();
    }

    private void getData(){
        Subscriber subscriber = getsubscriber();
      DescribeMethod.getInstance().getService("入学必备",subscriber);
    }
    public Subscriber<List<Describe_1>> getsubscriber(){
        Subscriber<List<Describe_1>> subscriber = new Subscriber<List<Describe_1>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(List<Describe_1> describe_1s) {
                for (Describe_1 describe_1:describe_1s){
                    view.describe(describe_1.getId(),describe_1.getName(),describe_1.getContent());
                }
            }
        };
        return subscriber;
    }

}

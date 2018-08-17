package com.mredrock.cyxbs.freshman.presenter.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.model.convert.Describe_1;
import com.mredrock.cyxbs.freshman.model.convert.JunXun;
import com.mredrock.cyxbs.freshman.model.http.httpmethods.HttpMethods;
import com.mredrock.cyxbs.freshman.presenter.base.BasePresenter;
import com.mredrock.cyxbs.freshman.view.view.JunxunHintView;
import com.mredrock.cyxbs.freshman.view.view.JunxunView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by 67698 on 2018/8/13.
 */

public class Junxun_hintPresenter extends BasePresenter {

    private Context mContext;
    private JunxunHintView junxunHintView;
    public Junxun_hintPresenter( JunxunHintView junxunHintView, Context mContext) {
        this.junxunHintView=junxunHintView;
        this.mContext = mContext;
        attachView(junxunHintView,mContext);
    }

    public Subscriber<List<Describe_1>> getsubscriber(){
        Subscriber<List<Describe_1>> subscriber = new Subscriber<List<Describe_1>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<Describe_1> describe_1s) {
                int size = describe_1s.size();
                for (int i = 0;i<size;i++){
                    Describe_1 describe_1 = describe_1s.get(i);
                    Log.d("Freshman", "onNext:调用 ");
                    junxunHintView.getJunxunHint(describe_1.getContent(),describe_1.getId());
                }
            }
        };
        return subscriber;
    }
    public void getData(){
        Subscriber subscriber = getsubscriber();
        HttpMethods.getInstance().getServiceOfDescribe(subscriber,"军训小贴士");
    }
}

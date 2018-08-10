package com.mredrock.cyxbs.freshman.presenter.presenter;

import android.content.Context;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.model.http.httpmethods.HttpMethods;
import com.mredrock.cyxbs.freshman.presenter.base.BasePresenter;
import com.mredrock.cyxbs.freshman.view.view.CampusView;

import java.util.List;

import rx.Subscriber;

public class CampusStrategyPresenter extends BasePresenter<CampusView> {

    private CampusView view;
    private Context mContent;

    public CampusStrategyPresenter(CampusView view, Context mContent) {
        this.view = view;
        this.mContent = mContent;
        attachView(view,mContext);
    }
    private Subscriber<List<Strategy>> getSubscriber(){
        return new Subscriber<List<Strategy>>() {
            @Override
            public void onCompleted() {
                view.onFinish();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(List<Strategy> list) {
                int size = list.size();
                for (int i = 0;i<size;i++){
                    view.getData(list.get(i));
                }
            }
        };
    }
    public void getData(String index,int pageNum,int pageSize){
        HttpMethods.getInstance().getServiceOfStrategy(getSubscriber(),index,pageNum,pageSize);
    }
}

package com.mredrock.cyxbs.freshman.presenter.presenter;

import android.content.Context;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.model.http.httpmethods.HttpMethods;
import com.mredrock.cyxbs.freshman.presenter.base.BasePresenter;
import com.mredrock.cyxbs.freshman.view.view.BedroomView;

import java.util.List;

import rx.Subscriber;

public class StudentBedRoomPresenter extends BasePresenter<BedroomView> {

    private BedroomView view;
    private Context mContext;

    public StudentBedRoomPresenter(BedroomView view, Context mContext) {
        this.view = view;
        this.mContext = mContext;
        attachView(view,mContext);
    }

    public void getbedroomAmount(){
        Subscriber subscriber = new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {
                view.getNameFinish();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            @Override
            public void onNext(List<String> list) {
                view.getBedroomName(list);
            }
        };
        HttpMethods.getInstance().getServiceOfAmount(subscriber,"学生寝室");
    }
    public void getData(String name){
        Subscriber subscriber = new Subscriber<List<Strategy>>() {
            @Override
            public void onCompleted() {
                view.getDataFinish();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            @Override
            public void onNext(List<Strategy> list) {
                for (int i = 0;i<list.size();i++){
                    Strategy strategy = list.get(i);
                    view.getData(strategy);
                }
            }
        };
        HttpMethods.getInstance().getServiceOfStudentRoom(subscriber,name);
    }
}

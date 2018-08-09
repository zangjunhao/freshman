package com.mredrock.cyxbs.freshman.presenter.presenter;

import android.content.Context;

import com.mredrock.cyxbs.freshman.model.convert.JunXun;
import com.mredrock.cyxbs.freshman.presenter.base.BasePresenter;
import com.mredrock.cyxbs.freshman.view.view.JunxunView;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class JunxunPresenter extends BasePresenter {
    private JunxunView junxunView;
    private Context mContext;

    public JunxunPresenter(JunxunView junxunView, Context mContext) {
        this.junxunView = junxunView;
        this.mContext = mContext;
        attachView(junxunView,mContext);
    }

    private Subscriber getSubscriber(){
        return new Subscriber<JunXun>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JunXun junXun) {
                if (junXun.getVideo()!=null){
                    for (String url:junXun.getVideo()){
                        junxunView.playVidio(url);
                    }
                }
                if (junXun.getPicture()!=null){
                    for (String url:junXun.getPicture()){
                        junxunView.viewImage(url);
                    }
                }
            }
        };
    }
}

package com.mredrock.cyxbs.freshman.presenter.presenter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.JunXun;
import com.mredrock.cyxbs.freshman.model.http.httpmethods.HttpMethods;
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

    public void showPhoto(String url)
    {
        Dialog dialog=new Dialog(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_largephoto, null, false);
        dialog.addContentView(view, new RelativeLayout.LayoutParams(dip2px(301), dip2px(316)));
        final ImageView bigView = (ImageView) dialog.findViewById(R.id.large_image);
        Glide.with(mContext).load(url).into(bigView);
        dialog.show();
    }

    public void getData(){
        Subscriber subscriber = new Subscriber<JunXun>() {
            @Override
            public void onCompleted() {
                junxunView.onFinish();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            @Override
            public void onNext(JunXun junXun) {
                junxunView.getJunxunlist(junXun);
                Log.d("video",junXun.toString());
            }
        };
        HttpMethods.getInstance().getServiceOfJunxun(subscriber);
    }
    private int dip2px(int dp)
    {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
    }
}

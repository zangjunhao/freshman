package com.mredrock.cyxbs.freshman.presenter.presenter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.JunXun;
import com.mredrock.cyxbs.freshman.model.http.httpmethods.HttpMethods;
import com.mredrock.cyxbs.freshman.presenter.base.BasePresenter;
import com.mredrock.cyxbs.freshman.view.tool.MyService;
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
        final Dialog dialog=new Dialog(mContext);
       int displayWidth = MyService.getDisplayWidth(mContext);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(displayWidth,displayWidth/2);
        ImageView imageView1 = new ImageView(mContext);
        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(mContext).load(url).into(imageView1);
        dialog.setContentView(imageView1,params);
        dialog.show();
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
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

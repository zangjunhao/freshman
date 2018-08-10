package com.mredrock.cyxbs.freshman.presenter.presenter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Describe_1;
import com.mredrock.cyxbs.freshman.model.http.httpmethods.HttpMethods;
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
        HttpMethods.getInstance().getServiceOfDescribe(subscriber,"入学必备");
    }
    public Subscriber<List<Describe_1>> getsubscriber(){
        Subscriber<List<Describe_1>> subscriber = new Subscriber<List<Describe_1>>() {
            @Override
            public void onCompleted() {
                view.onFinish();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(List<Describe_1> describe_1s) {
                int size = describe_1s.size();
                for (int i = 0;i<size;i++){
                    view.describe(describe_1s.get(i));
                }
            }
        };
        return subscriber;
    }
    public void showDialog(){
        final Dialog dialog = new Dialog(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.neccesary_dialog,null,false);
        TextView contentText = (TextView)view.findViewById(R.id.necessary_function_content);
        ImageView cancelView = (ImageView)view.findViewById(R.id.necessary_cancel_dialog);
        contentText.setText("hello");
        dialog.addContentView(view,new RelativeLayout.LayoutParams(dip2px(286),dip2px(272)));
        dialog.setCancelable(false);
        dialog.show();
        cancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public  int dip2px(int dp)
    {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
    }
}

package com.mredrock.cyxbs.freshman.presenter.presenter;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.cache.DatabaseUtil;
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
    private DatabaseUtil databaseUtil;
    private int version;
    private ContentValues values = new ContentValues();
    private static final String TABLE_NECESSARY = "necessary";

    public RuXuePresenter(RuXueView view, Context mContext) {
        attachView(view,mContext);
        this.view = view;
        this.mContext = mContext;
        databaseUtil = DatabaseUtil.DatabaseUtilHelper.getInstance();
        databaseUtil.initDatabasse(mContext,"Freshman.db",3);
        addData(null,null);
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
                    Describe_1 describe_1 = describe_1s.get(i);
                    view.describe(describe_1);
                    values.put("name",describe_1.getName());
                    values.put("content",describe_1.getContent());
                    databaseUtil.add(TABLE_NECESSARY,values);
                }
            }
        };
        return subscriber;
    }

    private void addData(String selection,String[] selectionArgs){
        Cursor cursor = databaseUtil.query(TABLE_NECESSARY,selection,selectionArgs);
        if (cursor.getCount()<10){
            getData();
            return;
        }
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                view.describe(new Describe_1(name,content));
            }while (cursor.moveToNext());
        }
        view.onFinish();
    }
    public void showDialog(){
        final Dialog dialog = new Dialog(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.neccesary_dialog,null,false);
       // TextView contentText = (TextView)view.findViewById(R.id.necessary_function_content);
        ImageView cancelView = (ImageView)view.findViewById(R.id.necessary_cancel_dialog);
        dialog.addContentView(view,new RelativeLayout.LayoutParams(dip2px(301),dip2px(316)));
        dialog.setCancelable(false);
        dialog.show();
        //dialog.getWindow().setContentView(view);
        cancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void addContent(String name){
        view.describe(new Describe_1(name,null));
        values.put("name",name);
        values.put("content","");
        databaseUtil.add(TABLE_NECESSARY,values);
    }

    public  int dip2px(int dp)
    {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
    }
}

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
import com.mredrock.cyxbs.freshman.model.convert.Describe;
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
    private ContentValues values = new ContentValues();
    private static final String TABLE_NECESSARY = "necessary";

    public RuXuePresenter(RuXueView view, Context mContext) {
        attachView(view,mContext);
        this.view = view;
        this.mContext = mContext;
        databaseUtil = DatabaseUtil.DatabaseUtilHelper.getInstance();
        databaseUtil.initDatabasse(mContext,"Freshman.db",4);
        addData();
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
                    describe_1.setNumber(100+i);
                    describe_1.setId(i);
                    view.describe(describe_1);
                    values.put("name",describe_1.getName());
                    values.put("content",describe_1.getContent());
                    values.put("number",100+i);
                    values.put("property",describe_1.getProperty());
                    databaseUtil.add(TABLE_NECESSARY,values);
                }
            }
        };
        return subscriber;
    }

    public void addData(){
        Cursor cursor = databaseUtil.querySort(TABLE_NECESSARY,"number");
        if (cursor.getCount()<10){
            getData();
            return;
        }
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                int number = cursor.getInt(cursor.getColumnIndex("number"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int oldPosition = cursor.getInt(cursor.getColumnIndex("oldPosition"));
                String property = cursor.getString(cursor.getColumnIndex("property"));
                Describe_1 describe_1 = new Describe_1(name,content);
                describe_1.setId(id);
                describe_1.setNumber(number);
                describe_1.setProperty(property);
                describe_1.setOldPosition(oldPosition);
                view.describe(describe_1);
            }while (cursor.moveToNext());
        }
        view.onFinish();
    }
    public void showDialog(){
        final Dialog dialog = new Dialog(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.neccesary_dialog,null,false);
        ImageView cancelView = (ImageView)view.findViewById(R.id.necessary_cancel_dialog);
        dialog.addContentView(view,new RelativeLayout.LayoutParams(dip2px(301),dip2px(316)));
        dialog.setCancelable(false);
        dialog.show();
        cancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void deleteData(String selection,String[] selectionArgs){
        databaseUtil.delete(TABLE_NECESSARY,selection,selectionArgs);
    }

    public void update(String key,String values,String selection){
        databaseUtil.update(TABLE_NECESSARY,key,values,selection);
    }

    public void addContent(String name){
        view.describe(new Describe_1(name,null));
        values.put("name",name);
        values.put("content","");
        values.put("number",200);
        values.put("oldPosition",0);
        values.put("property","非必需");
        databaseUtil.add(TABLE_NECESSARY,values);
    }

    public  int dip2px(int dp)
    {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
    }
}

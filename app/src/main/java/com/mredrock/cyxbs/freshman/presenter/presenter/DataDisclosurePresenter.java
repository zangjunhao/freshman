package com.mredrock.cyxbs.freshman.presenter.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.model.cache.DatabaseUtil;
import com.mredrock.cyxbs.freshman.model.http.httpmethods.HttpMethods;
import com.mredrock.cyxbs.freshman.presenter.base.BasePresenter;
import com.mredrock.cyxbs.freshman.view.view.DataDView;

import java.util.List;

import rx.Subscriber;

public class DataDisclosurePresenter extends BasePresenter<DataDView> {

    private Context mContext;
    private DataDView dataDView;
    private DatabaseUtil databaseUtil;
    private static final String COLLEGE_NAME_TABLE = "collegeName";

    public DataDisclosurePresenter(Context mContext, DataDView dataDView) {
        this.mContext = mContext;
        this.dataDView = dataDView;
        attachView(dataDView,mContext);
        this.mContext = mContext;
        databaseUtil = DatabaseUtil.DatabaseUtilHelper.getInstance();
        databaseUtil.initDatabasse(mContext,"Freshman.db",4);
        addData();
    }

    private void getData(){
        Subscriber subscriber = new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {
                view.onFinish();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            @Override
            public void onNext(List<String> list) {
                ContentValues values = new ContentValues();
                for (int i = 0;i<list.size();i++){
                    String name = list.get(i);
                    view.onData(name);
                    values.put("name",name);
                    databaseUtil.add(COLLEGE_NAME_TABLE,values);
                }
            }
        };
        HttpMethods.getInstance().getServiceOfGetName(subscriber);
    }

    public void addData(){
        Cursor cursor = databaseUtil.query(COLLEGE_NAME_TABLE,null,null);
        if (cursor.getCount()<5){
            getData();
            return;
        }else {
            if (cursor.moveToNext()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    view.onData(name);
                } while (cursor.moveToNext());
            }
        }
    }
}

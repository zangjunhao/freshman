package com.mredrock.cyxbs.freshman.presenter.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mredrock.cyxbs.freshman.model.cache.DatabaseUtil;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.model.http.httpmethods.HttpMethods;
import com.mredrock.cyxbs.freshman.presenter.base.BasePresenter;
import com.mredrock.cyxbs.freshman.view.view.CampusView;

import java.util.HashMap;
import java.util.List;

import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

public class CampusStrategyPresenter extends BasePresenter<CampusView> {

    private DatabaseUtil databaseUtil;
    private CampusView view;
    private Context mContext;
    private static final HashMap<String,String> STRATEGY_TABLE = new HashMap<>();
    private Gson gson = new Gson();
    public CampusStrategyPresenter(CampusView view, Context mContext) {
        this.view = view;
        this.mContext = mContext;
        attachView(view,mContext);
        databaseUtil = DatabaseUtil.DatabaseUtilHelper.getInstance();
        databaseUtil.initDatabasse(mContext,"Freshman.db",4);
        initTable();
    }
    private void initTable(){
        STRATEGY_TABLE.put("学生食堂","cafeteria");
        STRATEGY_TABLE.put("学生寝室","bedroom");
        STRATEGY_TABLE.put("周边美食","food");
        STRATEGY_TABLE.put("附近景点","views");
        STRATEGY_TABLE.put("校园环境","campus");
        STRATEGY_TABLE.put("附近银行","bank");
        STRATEGY_TABLE.put("公交线路","bus");
        STRATEGY_TABLE.put("快递收发","delivery");
        STRATEGY_TABLE.put("学生组织","organization");
        STRATEGY_TABLE.put("大型活动","activity");
    }
    private Subscriber<List<Strategy>> getSubscriber(String index){
        final String table = STRATEGY_TABLE.get(index);
        return new Subscriber<List<Strategy>>() {
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
            public void onNext(List<Strategy> list) {
                ContentValues values = new ContentValues();
                int size = list.size();
                for (int i = 0;i<size;i++){
                    Strategy strategy = list.get(i);
                    view.getData(strategy);
                    values.put("content",strategy.getContent());
                    values.put("id",strategy.getId());
                    String pictures = gson.toJson(strategy.getPicture());
                    values.put("picture",pictures );
                    values.put("name",strategy.getName());
                    databaseUtil.add(table,values);
                }
            }
        };
    }
    public void getData(String index,int pageNum,int pageSize){
        HttpMethods.getInstance().getServiceOfStrategy(getSubscriber(index),index,pageNum,pageSize);
    }
    public void addData(String index,int pageNum,int pageSize){
        String table = STRATEGY_TABLE.get(index);
        Cursor cursor = databaseUtil.querySort(table,"id");
        if (cursor.getCount()==0){
            getData(index,pageNum,pageSize);
            return;
        }else {
            if (cursor.moveToFirst()){
                do {
                    String content = cursor.getString(cursor.getColumnIndex("content"));
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String pictures = cursor.getString(cursor.getColumnIndex("picture"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    Strategy strategy = new Strategy();
                    strategy.setContent(content);
                    strategy.setId(id);
                    strategy.setName(name);
                    List<String> picture = gson.fromJson(pictures,new TypeToken<List<String>>(){}.getType());
                    strategy.setPicture(picture);
                    view.getData(strategy);
                }while (cursor.moveToNext());
            }
            view.onFinish();
        }
    }
}

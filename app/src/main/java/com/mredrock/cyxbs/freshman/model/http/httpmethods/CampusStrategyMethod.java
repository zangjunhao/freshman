package com.mredrock.cyxbs.freshman.model.http.httpmethods;

import com.mredrock.cyxbs.freshman.model.http.apiservice.CampusStrategyService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.JunXunService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CampusStrategyMethod {
    public static final String BASE_URL = "http://118.24.175.82/";
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;
    private CampusStrategyService campusStrategyService;

    private CampusStrategyMethod() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        campusStrategyService = retrofit.create(CampusStrategyService.class);
    }

    private static class SingleTonHolder {
        private static final CampusStrategyMethod HTTP_METHODS = new CampusStrategyMethod();
    }

    public static CampusStrategyMethod getInstance() {
        return SingleTonHolder.HTTP_METHODS;
    }

    public<T> void getService(Subscriber<T> s,String index,String pageNum,String pageSize) {
        Observable observable = campusStrategyService.getService(index,pageNum,pageSize);
        toSubscribe(observable,s);
    }

    private<T> void toSubscribe(Observable<T> observable,Subscriber s){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(s);
    }
}

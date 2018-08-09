package com.mredrock.cyxbs.freshman.model.http.httpmethods;


import com.mredrock.cyxbs.freshman.model.http.apiservice.HardService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HardSubjectMethod {

    public static final String BASE_URL = "http://118.24.175.82/";
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;
    private HardService hardService;

    private HardSubjectMethod() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        hardService = retrofit.create(HardService.class);
    }

    private static class SingleTonHolder {
        private static final HardSubjectMethod HTTP_METHODS = new HardSubjectMethod();
    }

    public static HardSubjectMethod getInstance() {
        return SingleTonHolder.HTTP_METHODS;
    }

    public<T> void getService(Subscriber<T> s,String name) {
        Observable observable = hardService.getService(name);
        toSubscribe(observable,s);
    }

    private<T> void toSubscribe(Observable<T> observable,Subscriber s){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(s);
    }
}

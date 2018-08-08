package com.mredrock.cyxbs.freshman.model.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HttpMethods {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;
    private ApiService apiService;

    private HttpMethods() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    private static class SingleTonHolder {
        private static final HttpMethods HTTP_METHODS = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return SingleTonHolder.HTTP_METHODS;
    }

    public<T> void getService(Observable observable,Subscriber<T> s) {
        observable = observable.map(new HttpResultFun());
        toSubscribe(observable,s);
    }

    private<T> void toSubscribe(Observable<T> observable,Subscriber<T> s){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(s);
    }

    private class HttpResultFun<T> implements Func1<HttpResult<T>,T> {
        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCode()==0){
                throw new ApiException("error");
            }
            return httpResult.getData();
        }
    }
}

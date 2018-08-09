package com.mredrock.cyxbs.freshman.model.http.httpmethods;

import com.mredrock.cyxbs.freshman.model.http.apiservice.GetNameService;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetNameMethods {
    public static final String BASE_URL = "http://118.24.175.82/";
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;
    private GetNameService getNameService;

    private GetNameMethods() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        getNameService = retrofit.create(GetNameService.class);
    }

    private static class SingleTonHolder {
        private static final GetNameMethods HTTP_METHODS = new GetNameMethods();
    }

    public static GetNameMethods getInstance() {
        return SingleTonHolder.HTTP_METHODS;
    }

    public<T> void getService(String path, Map<String,String> map, Subscriber<T> s) {
       Observable observable = getNameService.getService(path,map);
//                .map(new HttpResultFun<T>());
        toSubscribe(observable,s);
    }

    private<T> void toSubscribe(Observable<T> observable,Subscriber s){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(s);
    }

//    private class HttpResultFun<T> implements Func1<HttpResult<T>,T> {
//        @Override
//        public T call(HttpResult<T> httpResult) {
//            if (httpResult.getAmount()==0){
//                throw new ApiException("error");
//            }
//            return httpResult.getData();
//        }
//   }
}

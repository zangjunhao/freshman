package com.mredrock.cyxbs.freshman.model.http.httpmethods;

import com.mredrock.cyxbs.freshman.model.convert.Describe;
import com.mredrock.cyxbs.freshman.model.convert.Describe_1;
import com.mredrock.cyxbs.freshman.model.http.ApiException;
import com.mredrock.cyxbs.freshman.model.http.HttpResult;
import com.mredrock.cyxbs.freshman.model.http.apiservice.GetNameService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class DescribeMethod {
    public static final String BASE_URL = "http://118.24.175.82/";
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;
    private GetNameService getNameService;

    private DescribeMethod() {
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
        private static final DescribeMethod HTTP_METHODS = new DescribeMethod();
    }

    public static DescribeMethod getInstance() {
        return DescribeMethod.SingleTonHolder.HTTP_METHODS;
    }

    public<T> void getService(String path, Map<String,String> map, Subscriber<T> s) {
        Observable observable = getNameService.getService(path,map);
//                .map(new Func1<Describe,List<Describe_1>>() {
//
//                         @Override
//                         public List<Describe_1> call(Describe describe) {
//                             if (!(describe.getIndex().equals("入学必备") || describe.getIndex().equals("军训小贴士"))) {
//                                 throw new ApiException("error:index value error");
//                             } else {
//                                 return describe.getDescribe();
//                             }
//                         }
//                     }
//
//                );
        toSubscribe(observable,s);
    }

    private<T> void toSubscribe(Observable<T> observable,Subscriber s){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(s);
    }

}

package com.mredrock.cyxbs.freshman.model.http.httpmethods;

import com.mredrock.cyxbs.freshman.model.convert.CampusStrategy;
import com.mredrock.cyxbs.freshman.model.convert.Describe;
import com.mredrock.cyxbs.freshman.model.convert.Describe_1;
import com.mredrock.cyxbs.freshman.model.convert.JunXun;
import com.mredrock.cyxbs.freshman.model.http.ApiException;
import com.mredrock.cyxbs.freshman.model.http.apiservice.CampusStrategyService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.ChatGroupService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.DescribeService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.GetNameService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.HardService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.JunXunService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.SchoolService;

import java.util.List;
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

public class HttpMethods {
    public static final String BASE_URL = "http://118.24.175.82/";
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;

    private HttpMethods() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static class SingleTonHolder {
        private static final HttpMethods HTTP_METHODS = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return SingleTonHolder.HTTP_METHODS;
    }

    public<T> void getServiceOfJunxun(Subscriber<T> s) {

        Observable observable = retrofit.create(JunXunService.class).getService();
        toSubscribe(observable,s);
    }
    public<T> void getServiceOfStrategy(Subscriber<T> s,String index,String pageNum,String pageSize) {

        Observable observable = retrofit.create(CampusStrategyService.class).getService(index,pageNum,pageSize);
        toSubscribe(observable,s);
    }
    public<T> void getServiceOfChatGroup(Subscriber<T> s,String index) {

        Observable observable = retrofit.create(ChatGroupService.class).getService(index);
        toSubscribe(observable,s);
    }
    public<T> void getServiceOfDescribe(Subscriber<T> s,String index) {

        Observable observable = retrofit.create(DescribeService.class).getService(index)
                .map(new DescribeFun());
        toSubscribe(observable,s);
    }

    public<T> void getServiceOfGetName(Subscriber<T> s) {

        Observable observable = retrofit.create(GetNameService.class).getService();
        toSubscribe(observable,s);
    }

    public<T> void getServiceOfHardSubject(Subscriber<T> s,String name) {

        Observable observable = retrofit.create(HardService.class).getService(name);
        toSubscribe(observable,s);
    }

    public<T> void getServiceOfSchool(Subscriber<T> s,String name) {

        Observable observable = retrofit.create(SchoolService.class).getService(name);
        toSubscribe(observable,s);
    }

    private<T> void toSubscribe(Observable<T> observable,Subscriber s){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(s);
    }

    private class DescribeFun implements Func1<Describe,List<Describe_1>> {

        @Override
        public List<Describe_1> call(Describe describe) {
            if (!(describe.getIndex().equals("入学必备") || describe.getIndex().equals("军训小贴士"))) {
                throw new ApiException("error:index value error");
            } else {
                return describe.getDescribe();
            }
        }
    }
}

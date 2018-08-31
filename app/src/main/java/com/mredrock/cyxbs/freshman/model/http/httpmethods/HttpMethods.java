package com.mredrock.cyxbs.freshman.model.http.httpmethods;

import com.mredrock.cyxbs.freshman.model.convert.BelowSubject;
import com.mredrock.cyxbs.freshman.model.convert.CampusStrategy;
import com.mredrock.cyxbs.freshman.model.convert.Describe;
import com.mredrock.cyxbs.freshman.model.convert.Describe_1;
import com.mredrock.cyxbs.freshman.model.convert.GetAmount;
import com.mredrock.cyxbs.freshman.model.convert.GetGroup;
import com.mredrock.cyxbs.freshman.model.convert.GetName;
import com.mredrock.cyxbs.freshman.model.convert.Group_x_y;
import com.mredrock.cyxbs.freshman.model.convert.HardSubject;
import com.mredrock.cyxbs.freshman.model.convert.JunXun;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.model.convert.StudentRoom;
import com.mredrock.cyxbs.freshman.model.http.ApiException;
import com.mredrock.cyxbs.freshman.model.http.apiservice.CampusStrategyService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.ChatGroupService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.DescribeService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.GetAmountService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.GetNameService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.HardService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.JunXunService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.OnlinekeyService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.SchoolService;
import com.mredrock.cyxbs.freshman.model.http.apiservice.StudentRoomService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Single;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HttpMethods {
    public static final String BASE_URL = "http://wx.yyeke.com/welcome2018/";
    private static final int DEFAULT_TIMEOUT = 8;
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
    public<T> void getServiceOfStrategy(Subscriber<T> s,String index,int pageNum,int pageSize) {

        Observable observable = retrofit.create(CampusStrategyService.class).getService(index,pageNum,pageSize)
                .map(new Func1<CampusStrategy,List<Strategy>>() {

                    @Override
                    public List<Strategy> call(CampusStrategy campusStrategy) {
                        return campusStrategy.getArray();
                    }
                });
        toSubscribe(observable,s);
    }
    public<T> void getServiceOfChatGroup(Subscriber<T> s,String index) {

        Observable observable = retrofit.create(ChatGroupService.class).getService(index);
        toSubscribe(observable,s);
    }
    public<T> void getServiceOfOnlineKey(Subscriber<T> s,String index,String key) {

        Observable observable = retrofit.create(OnlinekeyService.class).getService(index,key)
                .map(new Func1<GetGroup,List<Group_x_y>>() {

                    @Override
                    public List<Group_x_y> call(GetGroup getGroup) {
                        return getGroup.getArray();
                    }
                });
        toSubscribe(observable,s);
    }
    public<T> void getServiceOfDescribe(Subscriber<T> s,String index) {

        Observable observable = retrofit.create(DescribeService.class).getService(index)
                .map(new DescribeFun());
        toSubscribe(observable,s);
    }

    public<T> void getServiceOfGetName(Subscriber<T> s) {

        Observable observable = retrofit.create(GetNameService.class).getService()
                .map(new Func1<GetName,List<String>>() {

                    @Override
                    public List<String> call(GetName getName) {
                        return getName.getName();
                    }
                });
        toSubscribe(observable,s);
    }

    public<T> void getServiceOfHardSubject(Subscriber<T> s,String name) {

        Observable observable = retrofit.create(HardService.class).getService(name)
                .map(new Func1<HardSubject,List<BelowSubject>>() {

                    @Override
                    public List<BelowSubject> call(HardSubject hardSubject) {
                        return hardSubject.getArray();
                    }
                });
        toSubscribe(observable,s);
    }

    public<T> void getServiceOfSchool(Subscriber<T> s,String name) {

        Observable observable = retrofit.create(SchoolService.class).getService(name);
        toSubscribe(observable,s);
    }

    public<T> void getServiceOfStudentRoom(Subscriber<T> s,String name){
        Observable observable = retrofit.create(StudentRoomService.class).getService(name)
                .map(new Func1<StudentRoom,List<Strategy>>() {

                    @Override
                    public List<Strategy> call(StudentRoom studentRoom) {
                        return studentRoom.getArray();
                    }
                });
        toSubscribe(observable,s);
    }
    public<T> void getServiceOfAmount(Subscriber<T> s,String index){
        Observable observable = retrofit.create(GetAmountService.class).getService(index)
               .map(new Func1<GetAmount,List<String>>() {

                   @Override
                   public List<String> call(GetAmount getAmount) {
                       return getAmount.getName();
                   }
               });
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

package com.mredrock.cyxbs.freshman.model.http.apiservice;

import com.mredrock.cyxbs.freshman.model.convert.Describe_1;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface DescribeService {
    @GET("data/get/describe")
    Observable<List<Describe_1>> getService(@Query("index")String index);
}

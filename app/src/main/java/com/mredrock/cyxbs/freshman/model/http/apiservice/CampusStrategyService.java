package com.mredrock.cyxbs.freshman.model.http.apiservice;

import com.mredrock.cyxbs.freshman.model.convert.CampusStrategy;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface CampusStrategyService {
    @GET("data/get/byindex")
    Observable<CampusStrategy> getService(@Query("index")String index, @Query("pagenum")int pageNum, @Query("pagesize")int pageSize);
}

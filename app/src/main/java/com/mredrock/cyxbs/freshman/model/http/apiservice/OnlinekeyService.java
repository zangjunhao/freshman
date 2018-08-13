package com.mredrock.cyxbs.freshman.model.http.apiservice;

import com.mredrock.cyxbs.freshman.model.convert.GetGroup;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface OnlinekeyService {
    @GET("search/chatgroup/abstractly")
    Observable<GetGroup> getService(@Query("index")String index,@Query("key") String key);
}

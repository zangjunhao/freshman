package com.mredrock.cyxbs.freshman.model.http.apiservice;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface HardService {
  @GET("search/school/2")
    Observable<HardService> getService(@Query("name")String name);
}

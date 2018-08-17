package com.mredrock.cyxbs.freshman.model.http.apiservice;

import com.mredrock.cyxbs.freshman.model.convert.HardSubject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface HardService {
  @GET("search/school/2")
    Observable<HardSubject> getService(@Query("name")String name);
}

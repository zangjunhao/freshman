package com.mredrock.cyxbs.freshman.model.http.apiservice;

import com.mredrock.cyxbs.freshman.model.convert.School;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface SchoolService {
    @GET("search/school/1")
    Observable<School> getService(@Query("name") String name);
}

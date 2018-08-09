package com.mredrock.cyxbs.freshman.model.http.apiservice;

import com.mredrock.cyxbs.freshman.model.convert.JunXun;

import retrofit2.http.GET;
import rx.Observable;

public interface JunXunService {
    @GET("data/get/Junxun")
    Observable<JunXun> getService();
}

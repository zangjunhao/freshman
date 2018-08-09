package com.mredrock.cyxbs.freshman.model.http.apiservice;

import com.mredrock.cyxbs.freshman.model.convert.GetGroup;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/*
*接口5
 */

public interface ChatGroupService {
    @GET("search/chatgroup/getgroup")
    Observable<GetGroup> getService(@Query("index")String index);
}

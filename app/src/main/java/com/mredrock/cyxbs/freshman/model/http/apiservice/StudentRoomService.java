package com.mredrock.cyxbs.freshman.model.http.apiservice;

import com.mredrock.cyxbs.freshman.model.convert.StudentRoom;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface StudentRoomService {
    @GET("data/get/sushe")
    Observable<StudentRoom> getService(@Query("name")String name);
}

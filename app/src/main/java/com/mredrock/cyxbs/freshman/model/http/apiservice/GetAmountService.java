package com.mredrock.cyxbs.freshman.model.http.apiservice;

import com.mredrock.cyxbs.freshman.model.convert.GetAmount;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GetAmountService {
    @GET("data/describe/getamount")
    Observable<GetAmount> getService(@Query("index")String index);
}

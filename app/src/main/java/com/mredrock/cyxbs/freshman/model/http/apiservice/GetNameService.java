package com.mredrock.cyxbs.freshman.model.http.apiservice;

import com.mredrock.cyxbs.freshman.model.convert.GetName;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface GetNameService {
    @GET("{path}")
    Observable<GetName> getService(@Path("path")String path, @QueryMap Map<String,String> map);
}

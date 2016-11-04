package com.nullproject.android.rxandroid_baisc09_retrofit;

import com.nullproject.android.rxandroid_baisc09_retrofit.com.nullproject.android.rxandroid_basic09_retrofit.domain.Data;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by YKC on 2016. 11. 4..
 */

public interface IWeather {
    // http://api.openweathermap.org
    // /data/2.5/weather?q=newyork&APPID=e166ef470d17b1be917512e72c8e312f

    @GET("/data/2.5/weather")
    Observable<Data> getData(@Query("q") String cityName, @Query("APPID") String key);
}

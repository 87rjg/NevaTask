package com.ram.nevatask;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by RAMJEE on 09-03-2018.
 */

public interface InterfaceApi {

    @GET("/")
    Observable<JsonResponse> getData();

}

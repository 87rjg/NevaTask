package com.ram.nevatask;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RAMJEE on 09-03-2018.
 */

public class ApiServices {

    public static final String  BASE_URL = "https://test-api.nevaventures.com";

    public static Retrofit retrofit = null;

    public static Retrofit getApi(){

        if(retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        }
        return retrofit;
    }
}

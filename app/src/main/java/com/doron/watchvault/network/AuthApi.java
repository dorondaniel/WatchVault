package com.doron.watchvault.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthApi {
    private static final String BASE_URL = "http://192.168.29.92:8080/";

    public static AuthApiService authApiGetter() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(AuthApiService.class);
    }
}



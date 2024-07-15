package com.doron.watchvault.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApiService {

    @POST("auth/login")
    Call<AuthModel> loginUser(@Body AuthModel authModel);

    @POST("/auth/save")
    Call<AuthModel> saveUser(@Body AuthModel authModel);
}

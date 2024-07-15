package com.doron.watchvault.network;

import com.doron.watchvault.network.models.AuthModel;
import com.doron.watchvault.network.models.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApiService {

    @POST("auth/login")
    Call<LoginResponseModel> loginUser(@Body AuthModel authModel);

    @POST("/auth/save")
    Call<AuthModel> saveUser(@Body AuthModel authModel);
}

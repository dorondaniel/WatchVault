package com.doron.watchvault.network;

import com.doron.watchvault.network.models.RVModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("discover/movie?api_key=89bb3ee8a23d187e5c04892a6440994a")
    Call <List<RVModel>> getPopularMovies();
}


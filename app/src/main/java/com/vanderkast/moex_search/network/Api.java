package com.vanderkast.moex_search.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface Api {
    @GET("authenticate")
    Call<String> auth(@Header("Authorization") String data);
}

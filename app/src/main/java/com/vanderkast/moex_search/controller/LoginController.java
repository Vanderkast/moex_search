package com.vanderkast.moex_search.controller;

import com.vanderkast.moex_search.model.LoginCredentials;
import com.vanderkast.moex_search.network.Api;
import com.vanderkast.moex_search.use_cases.LoginUseCase;

import javax.inject.Inject;

import android.util.Base64;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginController extends LoginUseCase {
    private final Api api;

    @Inject
    public LoginController(Api api) {
        this.api = api;
    }

    @Override
    protected String authorize(LoginCredentials credentials) throws AuthException {
        String data = "Basic " + new String(
                Base64.encode((credentials.getEmail() + ":" + credentials.getPassword()).getBytes(),
                        Base64.DEFAULT));
        api.auth(data).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.code() == 200)
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {

            }
        });
        return null;
    }

    @Override
    protected void saveCredentials(LoginCredentials credentials) {

    }

    @Override
    protected void saveToken(String token) {

    }
}

package com.vanderkast.moex_search.controller;

import android.util.Base64;
import com.vanderkast.moex_search.model.LoginCredentials;
import com.vanderkast.moex_search.network.LoginApi;
import com.vanderkast.moex_search.use_case.LoginUseCase;
import retrofit2.Response;

import javax.inject.Inject;
import java.io.IOException;

public class LoginController extends LoginUseCase {
    private final LoginApi loginApi;
    private final SaveCredentialsGateway saveCredentialsGateway;
    private final TokenKeeper.Provider tokenProvider;

    @Inject
    public LoginController(LoginApi loginApi, SaveCredentialsGateway saveCredentialsGateway, TokenKeeper.Provider tokenProvider) {
        this.loginApi = loginApi;
        this.saveCredentialsGateway = saveCredentialsGateway;
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected String authorize(LoginCredentials credentials) throws AuthException {
        String data = constructCredentials(credentials.getEmail(), credentials.getPassword());
        try {
            Response<String> response = loginApi.auth(data).execute();
            switch (response.code()) {
                case 200:
                    return response.body();
                case 401:
                    throw new WrongCredentials();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new NetworkError();
        }
        throw new NetworkError();
    }

    private String constructCredentials(String email, String password) {
        return "Basic " + new String(
                Base64.encode((email + ":" + password).getBytes(), Base64.DEFAULT)).trim();
    }

    @Override
    protected void saveCredentials(LoginCredentials credentials) {
        if(credentials.isNeedSave())
            saveCredentialsGateway.save(credentials.getEmail(), credentials.getPassword());
    }

    @Override
    protected void saveToken(String token) {
        tokenProvider.put(token);
    }

    public interface SaveCredentialsGateway {
        void save(String email, String password);
    }
}

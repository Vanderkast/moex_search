package com.vanderkast.moex_search.controller;

import com.vanderkast.moex_search.model.LoginCredentials;
import com.vanderkast.moex_search.use_cases.LoginUseCase;

import javax.inject.Inject;

public class LoginController extends LoginUseCase {
    @Inject
    public LoginController() {
    }

    @Override
    protected String authorize(LoginCredentials credentials) throws AuthException {
        return null;
    }

    @Override
    protected void saveCredentials(LoginCredentials credentials) {

    }

    @Override
    protected void saveToken(String token) {

    }
}

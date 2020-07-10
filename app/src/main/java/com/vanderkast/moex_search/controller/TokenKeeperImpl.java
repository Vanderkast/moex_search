package com.vanderkast.moex_search.controller;

import javax.inject.Singleton;

@Singleton
public class TokenKeeperImpl implements TokenKeeper, TokenKeeper.Provider {
    private String token;

    public TokenKeeperImpl() {
    }

    @Override
    public void put(String token) {
        this.token = token;
    }

    @Override
    public String get() {
        return token;
    }
}

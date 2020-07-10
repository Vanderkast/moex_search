package com.vanderkast.moex_search.controller;

public interface TokenKeeper {

    String get();

    interface Provider {
        void put(String token);
    }
}

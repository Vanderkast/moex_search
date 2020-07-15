package com.vanderkast.moex_search.controller;

import com.vanderkast.moex_search.entity.Stock;
import com.vanderkast.moex_search.use_case.StockUseCases;

import java.util.Set;
import java.util.function.Consumer;

public class StocksController extends StockUseCases {
    @Override
    protected void loadAll(Consumer<Set<Stock>> onDone) {

    }
}

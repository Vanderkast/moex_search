package com.vanderkast.moex_search.controller;

import com.vanderkast.moex_search.entity.Stock;
import com.vanderkast.moex_search.network.MainApi;
import com.vanderkast.moex_search.network.model.StockNetwork;
import com.vanderkast.moex_search.network.model.StockResponse;
import com.vanderkast.moex_search.use_case.StockUseCases;
import retrofit2.Response;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class StocksController extends StockUseCases {
    private final MainApi mainApi;

    @Inject
    public StocksController(MainApi mainApi) {
        this.mainApi = mainApi;
    }

    @Override
    protected void loadAll(Consumer<Set<Stock>> onDone) {
        try {
            Response<StockResponse> response = mainApi.getStockSharesMarketSecurities().execute();
            if (response.isSuccessful())
                onDone.accept(response.body().getStockList().stream().map(this::map).collect(Collectors.toSet()));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private Stock map(StockNetwork stockNetwork) {
        return new Stock(stockNetwork.getName(), stockNetwork.getPreviewPrice(), stockNetwork.getBoardId());
    }
}

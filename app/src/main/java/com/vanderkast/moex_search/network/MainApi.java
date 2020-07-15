package com.vanderkast.moex_search.network;

import com.vanderkast.moex_search.network.model.StockResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MainApi {

    @GET("iss/engines/stock/markets/shares/securities.xml")
    Call<StockResponse> getStockSharesMarketSecurities();
}

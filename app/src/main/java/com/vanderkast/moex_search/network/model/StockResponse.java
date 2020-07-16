package com.vanderkast.moex_search.network.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class StockResponse {
    @Path("data")
    @ElementList(name = "rows", type = StockNetwork.class)
    private List<StockNetwork> stockList;

    public List<StockNetwork> getStockList() {
        return stockList;
    }

    public void setStockList(List<StockNetwork> stockList) {
        this.stockList = stockList;
    }
}

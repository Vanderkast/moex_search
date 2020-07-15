package com.vanderkast.moex_search.network.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "document")
public class StockResponse {
    @ElementList(name = "rows")
    private List<StockNetwork> stockList;

    public List<StockNetwork> getStockList() {
        return stockList;
    }

    public void setStockList(List<StockNetwork> stockList) {
        this.stockList = stockList;
    }
}

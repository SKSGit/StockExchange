package com.company.entities;

import java.math.BigDecimal;

public interface Investor {
    void makeBid(Company party, int stocksToBuy, BigDecimal bid);
    boolean evaluateBid(BigDecimal bid, int stocksTheyWant);
    void setStockExchange(StockExchange stockExchange);
}

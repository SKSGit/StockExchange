package com.company.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface Investor {
    void makeBid(Bid bid);
    Bid acceptBid(List<Bid> bids);
    void setStockExchange(StockExchange stockExchange);
    void transferStock(Stock stock);
    boolean withdrawStock(Stock stock);
}

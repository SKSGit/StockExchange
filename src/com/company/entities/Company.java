package com.company.entities;

import java.math.BigDecimal;
import java.util.*;

public class Company implements Investor, Observer {
    Random rng = new Random();
    int stockAmount = 1000;
    BigDecimal stockPrice = BigDecimal.valueOf(30);
    String name;
    StockExchange stockExchange; //to be set in Dependency Injection

    public Company(String name) {
        this.name = name;
    }

    public void adjustingStockPrice() {
        stockPrice = BigDecimal.valueOf(stockAmount * 0.03);
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public BigDecimal getStockPrice() {
        return stockPrice;
    }


    @Override
    public void makeBid(Company party, int stocksToBuy, BigDecimal bid) {
        stockExchange.listBid(party, stocksToBuy, bid);
    }

    @Override
    public boolean evaluateBid(BigDecimal bid, int stocksTheyWant) {
        if (rng.nextInt(2) < 1) { //decide on coin flip. 0 or 1;
            return false;
        }
        return true;
    }

    public void stockSold() {
        stockAmount -= 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "stockAmount=" + stockAmount +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void setStockExchange(StockExchange stockExchange) {
        this.stockExchange = stockExchange;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(((HashMap<Company, Integer>) arg).toString());
    }


}

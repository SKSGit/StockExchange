package com.company.entities;

import java.math.BigDecimal;
import java.util.*;

public class Company implements Investor, Observer {
    Random rng = new Random();
    String name;
    StockExchange stockExchange; //to be set in Dependency Injection
    ArrayList<Stock> stocks;
    List<Bid> bids;


    public Company(String name) {
        this.name = name;
        this.stocks = new ArrayList<>(10);
        Collections.addAll(stocks, new Stock(this), new Stock(this), new Stock(this), new Stock(this), new Stock(this), new Stock(this), new Stock(this), new Stock(this), new Stock(this), new Stock(this));
    }

    public ArrayList<Stock> getStock() {
        return stocks;
    }

    public void buyBackStock() {
        //TODO: buy back own stock. Don't care about other companies stock.
    }

    @Override
    public void makeBid(Bid bid) {
        stockExchange.listBid(this, bid);
    }

    @Override
    public Bid acceptBid(List<Bid> bids) {
        //TODO: Make it so if a bid is accepted. It is removed from Exchange and the stock is transfered.
        bids = new ArrayList<>(stockExchange.getCompanyBids().values());
        if (bids == null) return null;
        Bid bestBid;
        try {
            bestBid = bids.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        for (int i = 0; i < bids.size(); i++) {
            if (bids.get(i).getStock().getCompany() != this) continue;
            if (bids.get(i).calculatePricePerStock().compareTo(bestBid.calculatePricePerStock()) > 0) bestBid = bids.get(i);
        }
        if (rng.nextInt(2) < 1) { //decide on coin flip. 0 or 1;
            return null;
        }

        if (bestBid.getStock().getCompany() != this) return null;
        return bestBid;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "stockAmount=" + getStock().size() +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean withdrawStock(Stock stock) {
        try {
            stocks.remove(stock);
            return true;
        } catch (Exception e) {
            System.out.println("couldnt withdraw stock");
            return false;
        }

    }

    @Override
    public void setStockExchange(StockExchange stockExchange) {
        this.stockExchange = stockExchange;
    }

    @Override
    public void transferStock(Stock stock) {

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(((HashMap<Company, Integer>) arg).keySet());
    }


}

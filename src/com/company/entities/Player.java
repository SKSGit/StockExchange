package com.company.entities;

import java.math.BigDecimal;
import java.util.*;

public class Player implements Investor, Observer {
    private BigDecimal funds;
    private UUID id;
    private String name;
    Random rng = new Random();
    private StockExchange stockExchange; //to be set in Dependency Injection

    public Player(BigDecimal funds, UUID id, String name) {
        this.funds = funds;
        this.id = id;
        this.name = name;
    }

    public void addInterest(double percent) {
        funds = funds.add(funds.multiply(BigDecimal.valueOf(percent)));
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "funds=" + funds.floatValue() +
                ", name='" + name + '\'' +
                '}';
    }


    @Override
    public void makeBid(Company party, int stocksToBuy, BigDecimal bid) {
        stockExchange.listBid(party, stocksToBuy, bid);
    }

    @Override
    public boolean evaluateBid(BigDecimal bid, int stocksTheyWant) {
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setStockExchange(StockExchange stockExchange) {
        this.stockExchange = stockExchange;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof StockExchange) {
            HashMap<Company, Integer> example = (HashMap<Company, Integer>) arg;
            makeBid(((Company) example.keySet().toArray()[rng.nextInt(2)]), rng.nextInt(3), BigDecimal.valueOf(rng.nextInt(3)));
            System.out.println(this.toString());
        }
    }
}

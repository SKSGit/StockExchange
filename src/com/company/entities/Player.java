package com.company.entities;

import java.math.BigDecimal;
import java.util.*;

public class Player implements Investor, Observer {
    private BigDecimal funds;
    private UUID id;
    private String name;
    private ArrayList<Stock> ownedStock;
    Random rng = new Random();
    private StockExchange stockExchange; //to be set in Dependency Injection

    public Player(BigDecimal funds, UUID id, String name) {
        this.funds = funds;
        this.id = id;
        this.name = name;
        this.ownedStock = new ArrayList<>();
    }

    public void transferStock(Stock stock) {
        ownedStock.add(stock);
    }

    @Override
    public boolean withdrawStock(Stock stock) {
        return false;
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

    public void sellStock() {
        //TODO: list a selling offer on the stock exchange. The offer being a specific stock for a certain price.
    }

    @Override
    public void makeBid(Bid bid) {
        stockExchange.listBid(this, bid);
    }

    @Override
    public Bid acceptBid(List<Bid> bids) {
        return null;
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
            HashMap<Company, ArrayList<Stock>> mapCompanyStocks = (HashMap<Company, ArrayList<Stock>>) arg;
            List<ArrayList<Stock>> listOfLists = new ArrayList<>(mapCompanyStocks.values());
            ArrayList<Stock> stocks = listOfLists.get(rng.nextInt(listOfLists.size()));

            if(stocks.size() != 0){
                makeBid(new Bid(this,
                        stocks.get(rng.nextInt(stocks.size())), 1, BigDecimal.valueOf(1+rng.nextInt(30))));
                System.out.println(this.toString()+this.ownedStock);
            }

        }
    }
}

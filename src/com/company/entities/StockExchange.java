package com.company.entities;

import com.company.Bid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class StockExchange extends Observable {
    private ArrayList<Observer> observers;
    private ArrayList<Bid> bids = new ArrayList<>();
    private HashMap<Company, Integer> companiesStock;
    private HashMap<Company, BigDecimal> bidsOnStock;


    public StockExchange() {
        this.observers = new ArrayList<>();
        this.companiesStock = new HashMap<>();
        this.bidsOnStock = new HashMap<>();
    }

    public void setObservers(){
        for (Observer obs : observers){
            if (obs instanceof Company){
                companiesStock.put((Company) obs, ((Company) obs).getStockAmount() );
            }
        }
    }

    public void listBid(Company party, int stocksToBuy, BigDecimal bid) {
        bidsOnStock.put(party, bid);
    }

    private void simulateJudgement() {
        //TODO Make transaction between Players, Computers and possibly Companies
        for (Observer observer : observers) {
            Investor investor = (Investor) observer;
            if (investor instanceof Company && investor.evaluateBid(bidsOnStock.get(investor), 1) == true) { //so far only buy 1 stock each update
                ((Company) investor).stockSold();
                ((Company) investor).adjustingStockPrice();
                companiesStock.replace(((Company) investor), ((Company) investor).getStockAmount());
            }
        }
    }

    public void updateTime() {
        simulateJudgement();
        setChanged();
        notifyObservers();
    }

    public HashMap<Company, Integer> getCompaniesStock() {
        return companiesStock;
    }

    @Override
    public synchronized void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(this, companiesStock);
        }
    }
}

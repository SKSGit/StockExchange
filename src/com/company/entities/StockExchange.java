package com.company.entities;

import java.math.BigDecimal;
import java.util.*;

public class StockExchange  extends Observable {
    private ArrayList<Observer> observers;
    private ArrayList<Bid> bids;
    private HashMap<Company, ArrayList<Stock>> companiesStock;
    private HashMap<Investor, Bid> bidsOnStock;


    public StockExchange() {
        this.observers = new ArrayList<>();
        this.companiesStock = new HashMap<>();
        this.bidsOnStock = new HashMap<>();
    }

    public void addCompaniesStock() {
        for (Observer obs : observers) {
            if (obs instanceof Company) {
                companiesStock.put((Company) obs, ((Company) obs).getStock());
            }
        }
    }

    public void listBid(Investor biddingParty, Bid bid) {
        bidsOnStock.put(biddingParty,bid);
    }

    private void simulateCompanyJudgement() {
        //TODO Make transaction between Players, Computers and possibly Companies
        //TODO: Make it so if a bid is accepted. It is removed from Exchange and the stock is transfered.
        //TODO: Make it so the stock is subtracted from available stocks a company offers.
        for (Observer observer : observers) {
            Investor investor = (Investor) observer;
            if (investor instanceof Company) { //so far only buy 1 stock each update
                try{
                    approveTransaction(investor, investor.acceptBid(new ArrayList<>( getCompanyBids().values())));
                } catch (NullPointerException e){
                    continue;
                }
            }
        }
    }

    public void updateTime() {
        simulateCompanyJudgement();
        setChanged();
        notifyObservers();
    }

    public void approveTransaction(Investor seller, Bid bid){
        bid.getBuyer().transferStock(bid.getStock());
        seller.withdrawStock(bid.getStock());
        bidsOnStock.remove(bid);
        if (seller instanceof Company){
            companiesStock.replace((Company) seller, ((Company) seller).getStock());
        }
    }

    public HashMap<Company, ArrayList<Stock>> getCompaniesStock() {
        return companiesStock;
    }

    public HashMap<Investor, Bid> getCompanyBids() {
        return bidsOnStock;
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

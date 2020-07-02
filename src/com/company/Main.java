package com.company;

import com.company.entities.*;

import java.math.BigDecimal;
import java.util.*;

public class Main {
//https://1000projects.org/final-year-distributed-system-project-idea-on-stock-market-simulation-game.html
    public static void main(String[] args) {
        Bank bank = new Bank();
        Player p = new Player(BigDecimal.valueOf(100), UUID.randomUUID(), "Sadik");
        Player p2 = new Player(BigDecimal.valueOf(100), UUID.randomUUID(), "Robot");
        Company company = new Company("Sadikman Sachs");

        Company heey = new Company("Stock Company");
        Company heey2 = new Company("Coke Company");
        Company heey3 = new Company("Potato Farm");
        Company heey4 = new Company("Digital Firm");



        ArrayList<Observer> allObservers = new ArrayList<>();

        allObservers.add(heey);
        allObservers.add(heey2);
        allObservers.add(heey3);
        allObservers.add(heey4);
        allObservers.add(company);
        allObservers.add(p);
        allObservers.add(p2);
        bank.addAccount(p);
        bank.addAccount(p2);
        bank.interestEndOfYear();
        StockExchange sE = new StockExchange();

        for (Observer observer : allObservers) {
            Investor investor = (Investor) observer;
            investor.setStockExchange(sE);
            sE.addObserver(observer);
        }
        sE.setObservers();

        long start = System.nanoTime();
        while (true) {
            long elapsedTime = System.nanoTime() - start;
            if (elapsedTime >= 10000000) {
                bank.interestEndOfYear();
                sE.updateTime();
                start = System.nanoTime();
            }

        }

    }
}

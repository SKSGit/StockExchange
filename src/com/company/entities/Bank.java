package com.company.entities;

import java.util.ArrayList;

public class Bank {
    Player[] accounts = new Player[10];

    double interestRate = 0.01;

    public Player[] getAccounts() {
        return accounts;
    }

    public void interestEndOfYear() {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) return;
            accounts[i].addInterest(interestRate);
        }
    }

    public void addAccount(Player account) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                System.out.println(account.getName()+" account added");
                return;
            }
        }
    }
}

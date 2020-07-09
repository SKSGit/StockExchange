package com.company.entities;

import java.util.UUID;

public class Stock {
    private Company company;
    private UUID id;

    public Stock(Company company) {
        this.company = company;
        id = UUID.randomUUID();
    }

    public Company getCompany() {
        return company;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "company=" + company +
                ", id=" + id +
                '}';
    }
}

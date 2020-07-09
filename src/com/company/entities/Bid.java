package com.company.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import com.company.entities.Company;

public class Bid {
    private Investor buyer;
    private BigDecimal offer;
    private Stock stock;
    private int amount;

    public Bid(Investor buyer, Stock stock, int amount, BigDecimal offer) {
        this.buyer = buyer;
        this.offer = offer;
        this.stock = stock;
        this.amount = amount;
    }

    public BigDecimal calculatePricePerStock() {
        return BigDecimal.valueOf(amount).divide(offer, RoundingMode.HALF_UP);
    }

    public Investor getBuyer() {
        return buyer;
    }



    public BigDecimal getOffer() {
        return offer;
    }


    public Stock getStock() {
        return stock;
    }
}

package com.company.entities;

import java.util.ArrayList;
import java.util.List;

public interface ExchangeInterface {
    ArrayList<Bid> getCompanyBids(Investor company);
}

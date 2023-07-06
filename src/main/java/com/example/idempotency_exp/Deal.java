package com.example.idempotency_exp;

import lombok.Data;

import java.util.Date;

@Data
public class Deal {
    String dealId;
    String buyer;
    String seller;
    double amount;
    String currency;
    Date dealDate;
    boolean isCompleted;
}

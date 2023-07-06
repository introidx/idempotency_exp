package com.example.idempotency_exp;

import lombok.Data;

@Data
public class Payment {
    private String paymentId;
    private String recipient;
    private double amount;
    private String currency;
    private String paymentMethod;
    private String paymentStatus;

}

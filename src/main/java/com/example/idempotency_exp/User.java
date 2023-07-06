package com.example.idempotency_exp;

import lombok.Data;

import java.util.Arrays;

@Data
public class User {
    String name;
    String phone;
    Payment[] payment;
    Deal[] deal;

}

package com.gibatekpro.ecommerce_spring.checkout.dto;

import lombok.Data;

@Data
public class PaymentInfo {

    public String receiptEmail;
    private int amount;
    private String currency;

    private String description;
}

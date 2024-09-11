package com.gibatekpro.ecommerce_spring.checkout.controller;

import com.gibatekpro.ecommerce_spring.checkout.dto.PaymentInfo;
import com.gibatekpro.ecommerce_spring.checkout.dto.Purchase;
import com.gibatekpro.ecommerce_spring.checkout.dto.PurchaseResponse;
import com.gibatekpro.ecommerce_spring.checkout.service.CheckoutService;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {


    CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {

        this.checkoutService = checkoutService;

    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {

        return checkoutService.placeOrder(purchase);

    }

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws Exception {

        PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);

        String paymentStr = paymentIntent.toJson();

        return new ResponseEntity<>(paymentStr, HttpStatus.OK);

    }

}

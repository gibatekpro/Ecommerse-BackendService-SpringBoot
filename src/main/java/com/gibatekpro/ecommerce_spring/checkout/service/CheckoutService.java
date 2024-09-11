package com.gibatekpro.ecommerce_spring.checkout.service;

import com.gibatekpro.ecommerce_spring.checkout.dto.PaymentInfo;
import com.gibatekpro.ecommerce_spring.checkout.dto.Purchase;
import com.gibatekpro.ecommerce_spring.checkout.dto.PurchaseResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Product;

import java.util.List;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;

}

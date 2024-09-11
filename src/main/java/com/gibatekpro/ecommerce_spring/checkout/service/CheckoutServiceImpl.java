package com.gibatekpro.ecommerce_spring.checkout.service;

import com.gibatekpro.ecommerce_spring.checkout.dto.PaymentInfo;
import com.gibatekpro.ecommerce_spring.checkout.repo.CustomerRepository;
import com.gibatekpro.ecommerce_spring.checkout.dto.Purchase;
import com.gibatekpro.ecommerce_spring.checkout.dto.PurchaseResponse;
import com.gibatekpro.ecommerce_spring.checkout.entity.Customer;
import com.gibatekpro.ecommerce_spring.checkout.entity.Order;
import com.gibatekpro.ecommerce_spring.checkout.entity.OrderItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CheckoutServiceImpl implements CheckoutService {

    CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository, @Value("${stripe.key.secret}") String secretKey) {

        this.customerRepository = customerRepository;

        //Initialize stripe api with secret key
        Stripe.apiKey = secretKey;
    }

    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {

        //retrieve the order info from dto
        Order order = purchase.getOrder();

        //generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        //populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        //populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());
        
        //populate customer with order
        Customer customer = purchase.getCustomer();

        // check if this is an existing customer
        String email = customer.getEmail();

        Customer customerFromDB = customerRepository.findByEmail(email);

        if (customerFromDB != null) {
            // we found them... let's assign them accordingly
            customer = customerFromDB;
        }

        customer.add(order);
        
        //save to the database
        customerRepository.save(customer);
        
        //return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {

        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfo.getAmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);
        params.put("description", paymentInfo.getDescription());
        params.put("receipt_email", paymentInfo.getReceiptEmail());


        return PaymentIntent.create(params);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}

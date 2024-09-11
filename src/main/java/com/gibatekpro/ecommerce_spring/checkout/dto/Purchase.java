package com.gibatekpro.ecommerce_spring.checkout.dto;

import com.gibatekpro.ecommerce_spring.checkout.entity.Address;
import com.gibatekpro.ecommerce_spring.checkout.entity.Customer;
import com.gibatekpro.ecommerce_spring.checkout.entity.Order;
import com.gibatekpro.ecommerce_spring.checkout.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}

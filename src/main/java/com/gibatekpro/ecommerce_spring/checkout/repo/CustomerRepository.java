package com.gibatekpro.ecommerce_spring.checkout.repo;

import com.gibatekpro.ecommerce_spring.checkout.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);

}

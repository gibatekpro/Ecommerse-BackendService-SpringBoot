package com.gibatekpro.ecommerce_spring.service;

import com.gibatekpro.ecommerce_spring.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    List<Product> findAll();

}

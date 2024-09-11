package com.gibatekpro.ecommerce_spring.controller;

import com.gibatekpro.ecommerce_spring.entity.Product;
import com.gibatekpro.ecommerce_spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/me")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/fetch")
    @ResponseBody
    public List<Product> findALl() {

        return productService.findAll();

    }

}

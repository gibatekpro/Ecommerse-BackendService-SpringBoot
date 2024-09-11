package com.gibatekpro.ecommerce_spring.repo;


import com.gibatekpro.ecommerce_spring.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-categories")
public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {
}

package com.gibatekpro.ecommerce_spring.repo;

import com.gibatekpro.ecommerce_spring.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

//@CrossOrigin
// We set spring.data.rest.detection-strategy to Annotated
@RepositoryRestResource
public interface ProductRepo extends JpaRepository<Product, Long> {

    //Spring Data Rest will create a link /search/findByCategoryId?id=(Long id)
    Page<Product> findByProductCategoryId(@Param("id") Long id, Pageable pageable);

    //Spring Data Rest will create a link /search/findByCategoryId?id=(Long id)
    Page<Product> findByNameContainsIgnoreCase(@Param("name") String name, Pageable pageable);

    @Query("SELECT p FROM Product p")
    List<Product> findAll();

}

package com.gibatekpro.ecommerce_spring.repo;

import com.gibatekpro.ecommerce_spring.entity.Country;
import com.gibatekpro.ecommerce_spring.entity.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

//@CrossOrigin
@RepositoryRestResource
public interface StateRepo extends JpaRepository<State, Integer> {

    //I have to add pageable
   Page<List<State>>  findStatesByCountry_Code(@Param("code") String code,
                                               Pageable pageable);

}

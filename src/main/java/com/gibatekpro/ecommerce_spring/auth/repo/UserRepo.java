package com.gibatekpro.ecommerce_spring.auth.repo;

import com.gibatekpro.ecommerce_spring.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);

}

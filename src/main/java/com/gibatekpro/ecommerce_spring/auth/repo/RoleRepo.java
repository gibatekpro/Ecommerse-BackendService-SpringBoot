package com.gibatekpro.ecommerce_spring.auth.repo;

import com.gibatekpro.ecommerce_spring.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface RoleRepo extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String roleName);

}

package com.gibatekpro.ecommerce_spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Generated;

import java.util.List;

@Entity
@Data
@Table(name = "country")
public class Country {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country")
    private List<State> states;

}

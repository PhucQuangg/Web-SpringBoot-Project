package com.vti.springboot_web.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zipCode")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    public Address() {
    }

    public Address(Integer id, String street, String city, String state, String zipCode, Customer customer) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.customer = customer;
    }
}

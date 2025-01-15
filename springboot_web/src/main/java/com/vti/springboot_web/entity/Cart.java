package com.vti.springboot_web.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "carts")
@Getter
@Setter
public class Cart {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "createdDate")
    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    private List<CartDetail> cartDetails;

    public Cart() {
    }

    public Cart(Integer id, LocalDate createdDate, Customer customer) {
        this.id = id;
        this.createdDate = createdDate;
        this.customer = customer;
    }
}

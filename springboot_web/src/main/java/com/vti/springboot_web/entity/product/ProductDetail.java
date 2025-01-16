package com.vti.springboot_web.entity.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_details")
@Getter
@Setter
public class ProductDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "Price")
    private Double price;
    @OneToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    public ProductDetail() {
    }

    public ProductDetail(Integer id, Integer quantity, Double price, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }
}

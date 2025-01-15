package com.vti.springboot_web.entity;

import com.vti.springboot_web.entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "cart_details")
@Getter
@Setter
public class CartDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cartId", referencedColumnName = "id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    public CartDetail() {
    }

    public CartDetail(Integer id, int quantity,Cart cart, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.cart = cart;
        this.product = product;
    }
}

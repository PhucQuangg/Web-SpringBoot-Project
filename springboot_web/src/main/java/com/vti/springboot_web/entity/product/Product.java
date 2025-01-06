package com.vti.springboot_web.entity.product;

import com.vti.springboot_web.entity.category.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "productName")
    private String productName;
    @Column(name = "price")
    private Double price;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;

    public Product() {
    }

    public Product(Integer id, String productName, Double price, String image, String description, Category category) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.description = description;
        this.category = category;
    }
}


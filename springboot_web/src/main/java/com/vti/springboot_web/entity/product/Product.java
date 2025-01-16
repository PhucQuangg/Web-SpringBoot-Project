package com.vti.springboot_web.entity.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "productCode")
    private String productCode;
    @Column(name = "productName")
    private String productName;
    @Column(name = "attributes")
    private String attributes;
    @Column(name = "supplier")
    private String supplier;
    @Column(name = "description")
    private String description;
    @Column(name = "images")
    private String image;
    @Column(name = "price")
    private Double price;
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "productLineId", referencedColumnName = "id")
    private ProductLine productLine;
    public Product() {
    }

    public Product(Integer id, String productCode, String productName, String attributes, String supplier, String description, String image, Double price, ProductDetail productDetail, ProductLine productLine) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.attributes = attributes;
        this.supplier = supplier;
        this.description = description;
        this.image = image;
        this.price = price;
        this.productDetail = productDetail;
        this.productLine = productLine;
    }
}



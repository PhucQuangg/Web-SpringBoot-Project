package com.vti.springboot_web.entity.product;

import com.vti.springboot_web.entity.Supplier;
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
    @Column(name = "productCode")
    private String productCode;
    @Column(name = "productName")
    private String productName;
    @Column(name = "description")
    private String description;
    @Column(name = "images")
    private String image;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "productLineId", referencedColumnName = "id")
    private ProductLine productLine;

    @ManyToOne
    @JoinColumn(name = "supplierId",referencedColumnName = "id")
    private Supplier supplier;

    public Product() {
    }

    public Product(Integer id, String productCode, String productName, String description, String image, ProductDetail productDetail, ProductLine productLine, Supplier supplier) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.description = description;
        this.image = image;
        this.productDetail = productDetail;
        this.productLine = productLine;
        this.supplier = supplier;
    }
}



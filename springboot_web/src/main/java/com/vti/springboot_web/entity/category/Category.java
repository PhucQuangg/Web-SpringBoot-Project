package com.vti.springboot_web.entity.category;

import com.vti.springboot_web.entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name =  "categoryName")
    private String categoryName;
    @Column(name = "categoryStatus")
    private Boolean categoryStatus;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category(Integer id, String categoryName, Boolean categoryStatus, Set<Product> products) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
        this.products = products;
    }

    public Category() {
    }
}

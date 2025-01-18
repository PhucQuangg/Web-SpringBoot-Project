package com.vti.springboot_web.entity.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ProductLine")
@Getter
@Setter
public class ProductLine {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name =  "name")
    private String name;
    @Column(name = "Status")
    private Boolean Status;

    @OneToMany(mappedBy = "productLine")
    private List<Product> product;

    public ProductLine() {
    }

    public ProductLine(Integer id, String name, Boolean status, List<Product> product) {
        this.id = id;
        this.name = name;
        Status = status;
        this.product = product;
    }
}

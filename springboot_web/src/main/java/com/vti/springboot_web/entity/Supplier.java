package com.vti.springboot_web.entity;

import com.vti.springboot_web.entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "Supliers")
@Entity
@Getter
@Setter
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "SupplierCode")
    private String code;
    @Column(name = "SupplierName")
    private String name;
    @Column(name = "Address")
    private String address;
    @Column(name = "Status")
    private String status;

    @OneToMany(mappedBy = "suppliers")
    private List<Product> product;

    public Supplier(Integer id, String code, String name, String address, String status, List<Product> product) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.address = address;
        this.status = status;
        this.product = product;
    }

    public Supplier() {
    }
}

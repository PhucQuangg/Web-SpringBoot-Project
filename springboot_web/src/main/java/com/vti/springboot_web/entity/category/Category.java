package com.vti.springboot_web.entity.category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    public Category(Integer id, String categoryName, Boolean categoryStatus) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    public Category() {
    }
}

package com.vti.springboot_web.repository;

import com.vti.springboot_web.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1%")
    List<Product> searchProduct(String keyword);

    Product findByProductName(String productName);
    Product findByProductCode(String productCode);

}

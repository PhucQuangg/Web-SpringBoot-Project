package com.vti.springboot_web.repository;

import com.vti.springboot_web.entity.product.Product;
import com.vti.springboot_web.entity.product.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductLineRepository extends JpaRepository<ProductLine,Integer> {
    @Query("SELECT p FROM ProductLine p WHERE p.name LIKE %?1%")
    List<ProductLine> searchProductLine(String keyword);
}

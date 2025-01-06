package com.vti.springboot_web.repository;

import com.vti.springboot_web.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}

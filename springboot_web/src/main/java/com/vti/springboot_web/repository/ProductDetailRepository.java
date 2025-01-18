package com.vti.springboot_web.repository;

import com.vti.springboot_web.entity.product.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail,Integer> {
}

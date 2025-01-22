package com.vti.springboot_web.repository;

import com.vti.springboot_web.entity.product.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail,Integer> {
    @Query("SELECT pd FROM ProductDetail pd" + "JOIN pd.product p WHERE" +
            "(p.productName LIKE %?1% OR ?1 IS NULL)"+
            "AND (pd.quantity = ?2 OR ?2 IS NULL)" +
            "AND (pd.price = ?3 OR ?3 IS NULL)")
    List<ProductDetail> searchProductDetail(String keyword);
}

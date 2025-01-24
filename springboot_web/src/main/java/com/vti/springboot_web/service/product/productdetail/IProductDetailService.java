package com.vti.springboot_web.service.product.productdetail;

import com.vti.springboot_web.entity.product.ProductDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductDetailService {
    List<ProductDetail> getAll();
    ProductDetail findById(Integer id);
    Boolean createDetail(ProductDetail productDetail);
    List<ProductDetail> searchProDetail(String keyword);
    Page<ProductDetail> getAllPageProDetails(Integer pageNum);
    Page<ProductDetail> searchProDetail(String keyword,Integer pageNum);
    Boolean updateDetail(ProductDetail productDetail);
}

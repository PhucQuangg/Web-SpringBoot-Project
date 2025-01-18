package com.vti.springboot_web.service.product.productdetail;

import com.vti.springboot_web.entity.product.ProductDetail;
import com.vti.springboot_web.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailService implements IProductDetailService{
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Override
    public List<ProductDetail> getAll() {
        return productDetailRepository.findAll();
    }
}

package com.vti.springboot_web.service.product;

import com.vti.springboot_web.entity.product.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Boolean createProduct(Product product);
    Product findById(Integer id);
    Boolean updateProduct(Product product);
    Boolean deleteProduct(Integer id);
}

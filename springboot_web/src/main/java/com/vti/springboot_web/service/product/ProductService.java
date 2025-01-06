package com.vti.springboot_web.service.product;

import com.vti.springboot_web.entity.product.Product;
import com.vti.springboot_web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Boolean createProduct(Product product) {
        try{
            productRepository.save(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product findById(Integer id) {
        return null;
    }

    @Override
    public Boolean updateProduct(Product product) {
        return null;
    }

    @Override
    public Boolean deleteProduct(Integer id) {
        return null;
    }
}

package com.vti.springboot_web.service.product;

import com.vti.springboot_web.entity.category.Category;
import com.vti.springboot_web.entity.product.Product;
import com.vti.springboot_web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean updateProduct(Product product) {
        try{
            productRepository.save(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteProduct(Integer id) {
        try{
            productRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        return productRepository.searchProduct(keyword);
    }

    @Override
    public Page<Product> getAllPageProduct(Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum-1,2);
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> searchProduct(String keyword, Integer pageNum) {
        List<Product> list = searchProduct(keyword);
        Pageable pageable = PageRequest.of(pageNum - 1, 2);
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), list.size());
        List<Product> subList = start > list.size() ? new ArrayList<>() : list.subList(start, end);
        return new PageImpl<>(subList, pageable, list.size());
    }

}

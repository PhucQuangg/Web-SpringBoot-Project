package com.vti.springboot_web.service.product.productline;

import com.vti.springboot_web.entity.product.ProductLine;
import com.vti.springboot_web.repository.ProductLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductLineService implements IProductLineService{
    @Autowired
    private ProductLineRepository productLineRepository;
    @Override
    public List<ProductLine> getAll() {
        return productLineRepository.findAll();
    }

    @Override
    public Boolean createProductLine(ProductLine productLine) {
        try {
            productLineRepository.save(productLine);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ProductLine findById(Integer id) {
        return productLineRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean updateProductLine(ProductLine productLine) {
        try {
            productLineRepository.save(productLine);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteProductLine(Integer id) {
        try{
            productLineRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ProductLine> searchProductLine(String keyword) {
        return productLineRepository.searchProductLine(keyword);
    }

    @Override
    public Page<ProductLine> getAllPageProductLine(Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum-1,2);
        return productLineRepository.findAll(pageable);
    }

    @Override
    public Page<ProductLine> searchProductLine(String keyword, Integer pageNum) {
        List<ProductLine> list = searchProductLine(keyword);
        Pageable pageable = PageRequest.of(pageNum - 1, 2);
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), list.size());
        List<ProductLine> subList = start > list.size() ? new ArrayList<>() : list.subList(start, end);
        return new PageImpl<>(subList, pageable, list.size());
    }
}

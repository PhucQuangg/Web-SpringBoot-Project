package com.vti.springboot_web.service.product.productdetail;

import com.vti.springboot_web.entity.product.ProductDetail;
import com.vti.springboot_web.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailService implements IProductDetailService{
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Override
    public List<ProductDetail> getAll() {
        return productDetailRepository.findAll();
    }

    @Override
    public ProductDetail findById(Integer id) {
        return productDetailRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean createDetail(ProductDetail productDetail) {
        try{
            productDetailRepository.save(productDetail);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ProductDetail> searchProDetail(String keyword) {
        return productDetailRepository.searchProductDetail(keyword);
    }

    @Override
    public Page<ProductDetail> getAllPageProDetails(Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum-1,2);
        return productDetailRepository.findAll(pageable);
    }

    @Override
    public Page<ProductDetail> searchProDetail(String keyword, Integer pageNum) {
        List<ProductDetail> list = searchProDetail(keyword);
        Pageable pageable = PageRequest.of(pageNum-1,2);
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), list.size());
        List<ProductDetail> sublist = start > list.size() ? new ArrayList<>() : list.subList(start,end);
        return new  PageImpl<>(sublist,pageable,list.size());
    }
}

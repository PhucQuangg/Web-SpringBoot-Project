package com.vti.springboot_web.service.category;

import com.vti.springboot_web.entity.category.Category;
import com.vti.springboot_web.entity.product.Product;
import com.vti.springboot_web.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Boolean createCate(Category category) {
        try{
            this.categoryRepository.save(category);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean updateCate(Category category) {
        try{
            this.categoryRepository.save(category);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteCate(Integer id) {
        try{
            this.categoryRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Category> searchCategory(String keyword) {
        return categoryRepository.searchCate(keyword);
    }

    @Override
    public Page<Category> getAllPage(Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum-1,2);
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<Category> searchCategory(String keyword, Integer pageNum) {
        List<Category> list = searchCategory(keyword);
        Pageable pageable = PageRequest.of(pageNum - 1, 2);
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), list.size());
        List<Category> subList = start > list.size() ? new ArrayList<>() : list.subList(start, end);
        return new PageImpl<>(subList, pageable, list.size());
    }

}

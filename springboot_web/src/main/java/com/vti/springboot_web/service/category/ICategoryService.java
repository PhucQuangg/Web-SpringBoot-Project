package com.vti.springboot_web.service.category;

import com.vti.springboot_web.entity.category.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService {
    List<Category> getAll();
    Boolean createCate(Category category);
    Category findById(Integer id);
    Boolean updateCate(Category category);
    Boolean deleteCate(Integer id);
    List<Category> searchCategory(String keyword);
    Page<Category> getAllPage(Integer pageNum);
    Page<Category> searchCategory(String keyword,Integer pageNum);


}

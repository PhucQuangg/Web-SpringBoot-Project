package com.vti.springboot_web.service.category;

import com.vti.springboot_web.entity.category.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAll();
    Boolean createCate(Category category);
    Category findById(Integer id);
    Boolean updateCate(Category category);
    Boolean deleteCate(Integer id);

}

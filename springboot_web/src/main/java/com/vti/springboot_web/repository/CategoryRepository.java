package com.vti.springboot_web.repository;

import com.vti.springboot_web.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}

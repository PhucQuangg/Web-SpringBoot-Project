package com.vti.springboot_web.controller.admin;

import com.vti.springboot_web.entity.category.Category;
import com.vti.springboot_web.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/category")
    public String index(Model model){
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("cateList",categoryList);
        return "admin/category/index";
    }

    @GetMapping("/add-category")
    public  String addCate(Model model){
        Category category = new Category();
        category.setCategoryStatus(true);
        model.addAttribute("category",category);
        return "admin/category/add";
    }

    @PostMapping("/add-category")
    private String saveCate(@ModelAttribute("category") Category category){
        if(categoryService.createCate(category)){
            return "redirect:/admin/category";
        }else {
            return "amin/category/add";
        }
    }

    @GetMapping("/edit-category/{id}")
    public String editCate(Model model, @PathVariable("id") Integer id){
        Category category = categoryService.findById(id);
        model.addAttribute("category",category);
        return "admin/category/edit";
    }

    @PostMapping("/edit-category")
    public String updateCate(@ModelAttribute("category") Category category){
        if(categoryService.updateCate(category)){
            return "redirect:/admin/category";
        }else {
            return "amin/category/add";
        }
    }

    @GetMapping("/delete-category/{id}")
    public String deleteCate(@PathVariable("id") Integer id){
        if(categoryService.deleteCate(id)){
            return "redirect:/admin/category";
        }else {
            return "redirect:/admin/category";
        }
    }



}

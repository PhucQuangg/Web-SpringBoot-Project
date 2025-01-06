package com.vti.springboot_web.controller.admin;

import com.vti.springboot_web.entity.category.Category;
import com.vti.springboot_web.entity.product.Product;
import com.vti.springboot_web.service.category.ICategoryService;
import com.vti.springboot_web.service.product.IProductService;
import com.vti.springboot_web.service.storage.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IStorageService storageService;

    @GetMapping("/product")
    public String index(Model model) {
        List<Product> productList = productService.getAll();
        model.addAttribute("productList",productList);
        return "admin/product/index";
    }

    @GetMapping("/product-add")
    public String addProduct(Model model){
        Product product = new Product();
        model.addAttribute("product",product);
        List<Category> listCate = categoryService.getAll();
        model.addAttribute("listCate",listCate);
        return "admin/product/add";
    }

    @PostMapping("/product-add")
    private String saveProduct(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file){
        storageService.store(file);
        String fileName = file.getOriginalFilename();
        product.setImage(fileName);
        if(productService.createProduct(product)){
            return "redirect:/admin/product";
        }else {
            return "admin/product/add";
        }
    }
}

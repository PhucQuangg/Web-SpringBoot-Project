package com.vti.springboot_web.controller.admin;

import com.vti.springboot_web.entity.Supplier;
import com.vti.springboot_web.entity.product.ProductLine;
import com.vti.springboot_web.entity.product.Product;
import com.vti.springboot_web.service.product.productline.IProductLineService;
import com.vti.springboot_web.service.product.IProductService;
import com.vti.springboot_web.service.storage.IStorageService;
import com.vti.springboot_web.service.supplier.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private IProductLineService productLineService;
    @Autowired
    private IProductService productService;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IStorageService storageService;

    @GetMapping("/product")
    public String index(Model model, @Param("keyword") String keyword, @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<Product> productList;
        if (keyword != null && !keyword.trim().isEmpty()) {
            productList = productService.searchProduct(keyword, pageNum);
            model.addAttribute("keyword", keyword);
        } else {
            productList = productService.getAllPageProduct(pageNum);
            model.addAttribute("keyword", "");
        }
        model.addAttribute("totalPage", productList.getTotalPages());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("productList", productList);
        return "admin/product/index";
    }


    @GetMapping("/product-add")
    public String addProduct(Model model){
        Product product = new Product();
        model.addAttribute("product",product);
        List<ProductLine> productLineList = productLineService.getAll();
        List<Supplier> supplierList = supplierService.getAll();
        model.addAttribute("productLineList",productLineList);
        model.addAttribute("supplierList",supplierList);
        return "admin/product/add";
    }

    @PostMapping("/product-add")
    private String saveProduct(Model model ,@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file){
        storageService.store(file);
        String fileName = file.getOriginalFilename();
        product.setImage(fileName);
        if(productService.createProduct(product)){
            return "redirect:/admin/product";
        }else {
            model.addAttribute("errorMessage", "Sản phẩm đã tồn tại!");
            List<ProductLine> productLineList = productLineService.getAll();
            List<Supplier> supplierList = supplierService.getAll();
            model.addAttribute("productLineList", productLineList);
           model.addAttribute("supplierList",supplierList);
            return "admin/product/add";
        }
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") Integer id){
        if(productService.deleteProduct(id)){
            return "redirect:/admin/product";
        }else {
            return "redirect:/admin/product";
        }
    }

    @GetMapping("/edit-product/{id}")
    public String editProduct(Model model, @PathVariable("id") Integer id){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        model.addAttribute("productLineList", productLineService.getAll());
        String imagePath = "/uploads/" + product.getImage();
        model.addAttribute("imagePath", imagePath);
        return "admin/product/edit";
    }

    @PostMapping("/edit-product")
    public String updateProduct(Model model,@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file){
        if (!file.isEmpty()) {
            storageService.store(file);
            product.setImage(file.getOriginalFilename());
        }else {
            product.setImage(product.getImage());
        }
        if (productService.updateProduct(product)) {
            return "redirect:/admin/product";
        } else {
            model.addAttribute("errorMessage", "Tên sản phẩm hoặc mã sản phẩm đã tồn tại!");
            List<ProductLine> productLineList = productLineService.getAll();
            model.addAttribute("productLineList", productLineList);
            return "admin/product/edit";
        }
    }
}

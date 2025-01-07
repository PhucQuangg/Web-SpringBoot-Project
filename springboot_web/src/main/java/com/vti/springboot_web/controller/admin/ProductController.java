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

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
        model.addAttribute("listCate", categoryService.getAll());
        String imagePath = "/uploads/" + product.getImage();
        model.addAttribute("imagePath", imagePath);
        model.addAttribute("imageName", product.getImage());
        return "admin/product/edit";
    }

    @PostMapping("/edit-product")
    public String updateProduct(@ModelAttribute("product") Product product,@RequestParam(value = "fileImage", required = false) MultipartFile file){
       if(!file.isEmpty()){
           try {
               // Lưu file hình ảnh vào thư mục uploads và lấy tên file
               String fileName = file.getOriginalFilename();
               String uploadDir = "/path/to/uploads/";  // Đảm bảo bạn thay thế bằng đường dẫn thực tế

               // Tạo đối tượng Path cho thư mục đích
               Path path = Path.of(uploadDir, fileName);

               // Sử dụng try-with-resources để tự động đóng các luồng
               try (InputStream inputStream = file.getInputStream()) {
                   Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
               }

               // Cập nhật tên hình ảnh vào đối tượng product
               product.setImage(fileName);
           } catch (IOException e) {
               e.printStackTrace();  // Xử lý lỗi hợp lý
               return "errorPage";  // Trang lỗi nếu không thể tải ảnh
           }
       }
       else{
           product.setImage(product.getImage());
       }
        if (productService.updateProduct(product)) {
            return "redirect:/admin/product";  // Chuyển hướng đến trang danh sách sản phẩm
        } else {
            return "admin/product/add";  // Trở lại trang thêm sản phẩm nếu có lỗi
        }
    }
}

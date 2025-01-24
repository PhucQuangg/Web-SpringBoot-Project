package com.vti.springboot_web.controller.admin;

import com.vti.springboot_web.entity.product.Product;
import com.vti.springboot_web.entity.product.ProductDetail;
import com.vti.springboot_web.entity.product.ProductLine;
import com.vti.springboot_web.service.product.IProductService;
import com.vti.springboot_web.service.product.productdetail.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductDetailController {
    @Autowired
    private IProductDetailService productDetailService;
    @Autowired
    private IProductService productService;
    @GetMapping("/productDetail")
    public String index(Model model, @Param("keyword") String keyword, @RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum){
//        List<ProductDetail> productDetailList = productDetailService.getAll();
//        model.addAttribute("productdetailList",productDetailList);
//        return "admin/product/detail/index";
        Page<ProductDetail> productDetailPage = productDetailService.getAllPageProDetails(pageNum);
        if(keyword != null){
            productDetailPage = productDetailService.searchProDetail(keyword, pageNum);
            model.addAttribute("keyword",keyword);
        }
        model.addAttribute("totalPage",productDetailPage.getTotalPages());
        model.addAttribute("currentPage",pageNum);
        model.addAttribute("productdetailList",productDetailPage);
        return "admin/product/detail/index";
    }

    @GetMapping("/add-detail")
    public String addDetail(Model model){
        ProductDetail productDetail = new ProductDetail();
        model.addAttribute("productdetail",productDetail);
        List<Product> productList = productService.getAll();
        model.addAttribute("productlist",productList);
        return "admin/product/detail/add";
    }

    @PostMapping("/add-detail")
    public String saveDetail(Model model,@ModelAttribute("productdetail") ProductDetail productDetail){
        if(productDetailService.createDetail(productDetail)){
            return "redirect:/admin/productDetail";
        }
        else {
            model.addAttribute("errorMessage", "Chi tiết sản phẩm đã tồn tại!");
            model.addAttribute("productlist", productService.getAll());
            return "admin/product/detail/add";
        }
    }

    @GetMapping("/edit-detail/{id}")
    public String editDetail(Model model,@PathVariable("id") Integer id){
        ProductDetail productDetail = productDetailService.findById(id);
        model.addAttribute("productdetail",productDetail);
        model.addAttribute("productlist",productService.getAll());
        return "admin/product/detail/edit";
    }
    @PostMapping("/edit-detail")
    public String updateDetail(@ModelAttribute("productdetail") ProductDetail productDetail){
        if(productDetailService.updateDetail(productDetail)){
            return "redirect:/admin/productDetail";
        }
        else {
            return "admin/product/detail/edit";
        }
    }
}

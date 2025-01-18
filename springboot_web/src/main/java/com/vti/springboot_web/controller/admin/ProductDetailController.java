package com.vti.springboot_web.controller.admin;

import com.vti.springboot_web.entity.product.ProductDetail;
import com.vti.springboot_web.entity.product.ProductLine;
import com.vti.springboot_web.service.product.productdetail.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductDetailController {
    @Autowired
    private IProductDetailService productDetailService;
    @GetMapping("/productDetail")
    public String index(Model model ){
        return "admin/product/linePro/index";
    }
}

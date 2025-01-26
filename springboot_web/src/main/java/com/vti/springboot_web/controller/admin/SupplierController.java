package com.vti.springboot_web.controller.admin;

import com.vti.springboot_web.entity.Supplier;
import com.vti.springboot_web.service.supplier.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/Supplier")
    public String index(Model model){
        List<Supplier> supplierList = supplierService.getAll();
        model.addAttribute("supplierList",supplierList);
        return "admin/supplier/index";
    }
}

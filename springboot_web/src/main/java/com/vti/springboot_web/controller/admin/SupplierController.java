package com.vti.springboot_web.controller.admin;

import com.vti.springboot_web.entity.Supplier;
import com.vti.springboot_web.service.supplier.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/Supplier-add")
    public String addSupplier(Model model){
        Supplier supplier = new Supplier();
        supplier.setStatus(true);
        model.addAttribute("supplier",supplier);
        return  "admin/supplier/add";
    }
    @PostMapping("/Supplier-add")
    public String saveSupplier(Model model, @ModelAttribute("supplier") Supplier supplier){
        if(supplierService.createSuppliers(supplier)){
            return "redirect:/admin/Supplier";
        }
        else {
            model.addAttribute("errorMessage","Nhà cung cấp đã tồn tại");
            return "admin/supplier/add";
        }
    }

    @GetMapping("/delete-supplier/{id}")
    public String deleteSupplier(RedirectAttributes redirectAttributes, @PathVariable("id") Integer id){
        if (supplierService.deleteSuppliers(id)){
            return "redirect:/admin/Supplier";
        }
        else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa nhà cung cấp vì có sản phẩm liên quan.");
            return "redirect:/admin/Supplier";
        }
    }

    @GetMapping("/edit-supplier/{id}")
    public String editSupplier(Model model, @PathVariable("id") Integer id){
        Supplier supplier= supplierService.findById(id);
        model.addAttribute("supplier",supplier);
        return "admin/supplier/edit";
    }
    @PostMapping("/edit-supplier")
    public String updateSupplier(Model model,@ModelAttribute("supplier") Supplier supplier){
        if(supplierService.updateSuppliers(supplier)){
            return "redirect:/admin/Supplier";
        }
        else {
            model.addAttribute("errorMessage", "Tên nhà cung cấp hoặc mã nhà cung cấp đã tồn tại!");
            return "admin/supplier/edit";
        }
    }


}

package com.vti.springboot_web.controller.admin;

import com.vti.springboot_web.entity.product.ProductLine;
import com.vti.springboot_web.service.product.productline.IProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class ProductLineController {
    @Autowired
    private IProductLineService productLineService;

    @GetMapping("/productLine")
    public String index(Model model, @Param("keyword") String keyword, @RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum){

        Page<ProductLine> productLineList = productLineService.getAllPageProductLine(pageNum);
        if(keyword !=null){
            productLineList = productLineService.searchProductLine(keyword,pageNum);
            model.addAttribute("keyword",keyword);
        }
        model.addAttribute("totalPage",productLineList.getTotalPages());
        model.addAttribute("currentPage",pageNum);
        model.addAttribute("productLineList",productLineList);
        return "admin/product/linePro/index";
    }

    @GetMapping("/add-productline")
    public  String addLine(Model model){
        ProductLine productLine = new ProductLine();
        productLine.setStatus(true);
        model.addAttribute("productLine", productLine);
        return "admin/product/linePro/add";
    }

    @PostMapping("/add-productline")
    private String saveLine(Model model,@ModelAttribute("productLine") ProductLine productLine){
        if(productLineService.createProductLine(productLine)){
            return "redirect:/admin/productLine";
        }else {
            model.addAttribute("errorMessage", "Dòng sản phẩm đã tồn tại!");
            return "admin/product/linePro/add";
        }
    }

    @GetMapping("/edit-productLine/{id}")
    public String editLine(Model model, @PathVariable("id") Integer id){
        ProductLine productLine = productLineService.findById(id);
        model.addAttribute("productLine", productLine);
        return "admin/product/linePro/edit";
    }

    @PostMapping("/edit-productLine")
    public String updateLine(Model model,@ModelAttribute("productLine") ProductLine productLine){
        if(productLineService.updateProductLine(productLine)){
            return "redirect:/admin/productLine";
        }else {
            model.addAttribute("errorMessage", "Dòng sản phẩm đã tồn tại!");
            return "admin/product/linePro/edit";
        }
    }

    @GetMapping("/delete-productLine/{id}")
    public String deleteLine(RedirectAttributes redirectAttributes, @PathVariable("id") Integer id){
        if(productLineService.deleteProductLine(id)){
            return "redirect:/admin/productLine";
        }else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa dòng sản phẩm vì có sản phẩm liên quan.");
            return "redirect:/admin/productLine";
        }
    }


}

package com.vti.springboot_web.service.product.productline;

import com.vti.springboot_web.entity.product.ProductLine;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductLineService {
    List<ProductLine> getAll();
    Boolean createProductLine(ProductLine productLine);
    ProductLine findById(Integer id);
    Boolean updateProductLine(ProductLine productLine);
    Boolean deleteProductLine(Integer id);
    List<ProductLine> searchProductLine(String keyword);
    Page<ProductLine> getAllPageProductLine(Integer pageNum);
    Page<ProductLine> searchProductLine(String keyword,Integer pageNum);
}

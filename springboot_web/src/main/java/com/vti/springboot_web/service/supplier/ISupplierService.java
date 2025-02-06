package com.vti.springboot_web.service.supplier;


import com.vti.springboot_web.entity.Supplier;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISupplierService {
    List<Supplier> getAll();
    Boolean createSuppliers(Supplier supplier);
    Boolean deleteSuppliers(Integer id);
    Boolean updateSuppliers(Supplier supplier);
    Supplier findById(Integer id);
    List<Supplier> searchSupplier(String keyword);
    Page<Supplier> getAllPageSupplier(Integer pageNum);
    Page<Supplier> searchSupplier(String keyword, Integer pageNum);
}

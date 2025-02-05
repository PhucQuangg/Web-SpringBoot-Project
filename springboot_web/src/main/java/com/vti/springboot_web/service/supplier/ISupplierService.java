package com.vti.springboot_web.service.supplier;


import com.vti.springboot_web.entity.Supplier;

import java.util.List;

public interface ISupplierService {
    List<Supplier> getAll();
    Boolean createSuppliers(Supplier supplier);
    Boolean deleteSuppliers(Integer id);
    Boolean updateSuppliers(Supplier supplier);
    Supplier findById(Integer id);
}

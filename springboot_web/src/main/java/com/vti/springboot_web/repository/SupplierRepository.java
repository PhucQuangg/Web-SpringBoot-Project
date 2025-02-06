package com.vti.springboot_web.repository;

import com.vti.springboot_web.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    Supplier findByCode(String supplierCode);
    Supplier findByName(String supplierName);

    @Query("SELECT s FROM Supplier s WHERE s.name LIKE %?1%")
    List<Supplier> searchSupplier(String keyword);
}

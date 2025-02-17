package com.vti.springboot_web.service.supplier;

import com.vti.springboot_web.entity.Supplier;
import com.vti.springboot_web.entity.product.Product;
import com.vti.springboot_web.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService implements ISupplierService{
    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    public Boolean isSupplierCodeExist(String supplierCode){
        Supplier exists = supplierRepository.findByCode(supplierCode);
        return exists != null;
    }
    public Boolean isSupplierExist(String supplierName){
        Supplier exists = supplierRepository.findByName(supplierName);
        return exists != null;
    }

    @Override
    public Boolean createSuppliers(Supplier supplier) {
        try{
            if (isSupplierCodeExist(supplier.getCode())) {
                return false;
            } else if(isSupplierExist(supplier.getName())){
                return false;
            }

            supplierRepository.save(supplier);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteSuppliers(Integer id) {
        try{
            supplierRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateSuppliers(Supplier supplier) {
        try{
            Supplier existSupplier = supplierRepository.findByCode(supplier.getCode());

            if (existSupplier != null && !existSupplier.getId().equals(supplier.getId())) {
                return false;
            }

            existSupplier = supplierRepository.findByName(supplier.getName());
            if (existSupplier != null && !existSupplier.getId().equals(supplier.getId())) {
                return false;
            }
            supplierRepository.save(supplier);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Supplier findById(Integer id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public List<Supplier> searchSupplier(String keyword) {
        return supplierRepository.searchSupplier(keyword);
    }

    @Override
    public Page<Supplier> getAllPageSupplier(Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum-1,2);
        return supplierRepository.findAll(pageable);
    }

    @Override
    public Page<Supplier> searchSupplier(String keyword, Integer pageNum) {
        List<Supplier> list = searchSupplier(keyword);
        Pageable pageable = PageRequest.of(pageNum-1,2);
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), list.size());
        List<Supplier> subList = start > list.size() ? new ArrayList<>():list.subList(start,end);
        return new PageImpl<>(subList,pageable,list.size());
    }
}

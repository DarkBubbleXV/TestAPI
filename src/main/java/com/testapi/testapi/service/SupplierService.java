package com.testapi.testapi.service;

import com.testapi.testapi.model.Supplier;
import com.testapi.testapi.repository.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    public Supplier addSupplier(Supplier supplier){
        return supplierRepo.save(supplier);
    }

    public List<Supplier> findAll(){
        return supplierRepo.findAll();
    }

    public Supplier findById(Long supplierId){
        return supplierRepo.findById(supplierId).get();
    }

    public void removeSupplierById(Long supplierId){
        supplierRepo.deleteById(supplierId);
    }
}

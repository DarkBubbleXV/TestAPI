package com.testapi.testapi.controller;

import com.testapi.testapi.dto.RespondDto;
import com.testapi.testapi.model.Supplier;
import com.testapi.testapi.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<RespondDto<Supplier>> addSupplier(@Valid @RequestBody Supplier supplier, Errors errors){

        RespondDto<Supplier> supplierRespondDto = new RespondDto<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                supplierRespondDto.getMessage().add(error.getDefaultMessage());
            }
            supplierRespondDto.setStatus(false);
            supplierRespondDto.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(supplierRespondDto);
        }
        supplierRespondDto.setStatus(true);
        supplierRespondDto.setPayload(supplierService.addSupplier(supplier));
        return ResponseEntity.ok(supplierRespondDto);
    }

    @GetMapping
    public List<Supplier> findAll(){
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Supplier findById(@PathVariable("id") Long supplierId){
        return supplierService.findById(supplierId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long supplierId){
        supplierService.removeSupplierById(supplierId);
    }

}

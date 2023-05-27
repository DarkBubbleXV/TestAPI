package com.testapi.testapi.controller;

import com.testapi.testapi.dto.RespondDto;
import com.testapi.testapi.model.Product;
import com.testapi.testapi.model.Supplier;
import com.testapi.testapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<RespondDto<Product>> addProduct(@Valid @RequestBody Product product, Errors errors){

        RespondDto<Product> productRespondDto = new RespondDto<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                productRespondDto.getMessage().add(error.getDefaultMessage());
            }
            productRespondDto.setStatus(false);
            productRespondDto.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productRespondDto);
        }
        productRespondDto.setStatus(true);
        productRespondDto.setPayload(productService.addProduct(product));
        return ResponseEntity.ok(productRespondDto);
    }

    @GetMapping
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long productId){
        return productService.findById(productId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(Long productId){
        productService.removeById(productId);
    }

    @PostMapping("/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId){
        productService.addSupplier(supplier, productId);
    }
}

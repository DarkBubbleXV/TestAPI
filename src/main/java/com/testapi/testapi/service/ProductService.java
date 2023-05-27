package com.testapi.testapi.service;

import com.testapi.testapi.model.Product;
import com.testapi.testapi.model.Supplier;
import com.testapi.testapi.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product addProduct(Product product){
        return productRepo.save(product);
    }

    public List<Product> findAll(){
        return productRepo.findAll();
    }

    public Product findById(Long productId){
        return productRepo.findById(productId).get();
    }

    public void removeById(Long productId){
        productRepo.deleteById(productId);
    }

    public void addSupplier(Supplier supplier, Long productId){
        Product product = findById(productId);
        if(product == null){
            throw new RuntimeException("Product with ID" + productId + "not found");
        }
        product.getSuppliers().add(supplier);
        addProduct(product);
    }
}

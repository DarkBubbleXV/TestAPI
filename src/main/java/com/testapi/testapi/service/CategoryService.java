package com.testapi.testapi.service;

import com.testapi.testapi.model.Category;
import com.testapi.testapi.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category addCategory(Category category){
        return categoryRepo.save(category);
    }

    public List<Category> findAll(){
        return categoryRepo.findAll();
    }

    public Category findById(Long categoryId){
        return categoryRepo.findById(categoryId).get();
    }

    public void removeById(Long categoryId){
        categoryRepo.deleteById(categoryId);
    }
}

package com.testapi.testapi.controller;

import com.testapi.testapi.dto.RespondDto;
import com.testapi.testapi.model.Category;
import com.testapi.testapi.model.User;
import com.testapi.testapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<RespondDto<Category>> addCategory(@Valid @RequestBody Category category, Errors errors){

        RespondDto<Category> categoryRespondDto = new RespondDto<>();
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                categoryRespondDto.getMessage().add(error.getDefaultMessage());
            }
            categoryRespondDto.setStatus(false);
            categoryRespondDto.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(categoryRespondDto);
        }
        categoryRespondDto.setStatus(true);
        categoryRespondDto.setPayload(categoryService.addCategory(category));
        return ResponseEntity.ok(categoryRespondDto);
    }

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable("id") Long categoryId){
        return categoryService.findById(categoryId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long categoryId){
        categoryService.removeById(categoryId);
    }
}

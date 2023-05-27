package com.testapi.testapi.controller;

import com.testapi.testapi.dto.RespondDto;
import com.testapi.testapi.model.Author;
import com.testapi.testapi.model.Book;
import com.testapi.testapi.model.Supplier;
import com.testapi.testapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<RespondDto<Author>> addAuthor(@Valid @RequestBody Author author, Errors errors){

        RespondDto<Author> authorRespondDto = new RespondDto<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                authorRespondDto.getMessage().add(error.getDefaultMessage());
            }
            authorRespondDto.setStatus(false);
            authorRespondDto.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authorRespondDto);
        }
        authorRespondDto.setStatus(true);
        authorRespondDto.setPayload(authorService.addAuthor(author));
        return ResponseEntity.ok(authorRespondDto);
    }

    @GetMapping
    public List<Author> findAll(){
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable("id") Long authorId){
        return authorService.findById(authorId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long authorId){
        authorService.removeAuthorById(authorId);
    }

    @PostMapping("/{id}")
    public void addSupplier(@RequestBody Book book, @PathVariable("id") Long authorId){
        authorService.addBook(book, authorId);
    }
}

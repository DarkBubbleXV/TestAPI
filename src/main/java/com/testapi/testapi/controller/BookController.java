package com.testapi.testapi.controller;

import com.testapi.testapi.dto.RespondDto;
import com.testapi.testapi.model.Book;
import com.testapi.testapi.model.Product;
import com.testapi.testapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<RespondDto<Book>> addBook(@Valid @RequestBody Book book, Errors errors){

        RespondDto<Book> bookRespondDto = new RespondDto<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                bookRespondDto.getMessage().add(error.getDefaultMessage());
            }
            bookRespondDto.setStatus(false);
            bookRespondDto.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bookRespondDto);
        }
        bookRespondDto.setStatus(true);
        bookRespondDto.setPayload(bookService.addBook(book));
        return ResponseEntity.ok(bookRespondDto);
    }

    @GetMapping
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable("id") Long bookId){
        return bookService.findById(bookId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long bookId){
        bookService.removeById(bookId);
    }
}

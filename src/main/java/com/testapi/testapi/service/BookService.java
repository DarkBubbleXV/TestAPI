package com.testapi.testapi.service;

import com.testapi.testapi.model.Book;
import com.testapi.testapi.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public Book addBook(Book book){
        return bookRepo.save(book);
    }

    public List<Book> findAll(){
        return bookRepo.findAll();
    }

    public Book findById(Long bookId){
        return bookRepo.findById(bookId).get();
    }

    public void removeById(Long bookId){
        bookRepo.deleteById(bookId);
    }

}

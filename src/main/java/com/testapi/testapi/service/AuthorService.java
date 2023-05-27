package com.testapi.testapi.service;

import com.testapi.testapi.model.Author;
import com.testapi.testapi.model.Book;
import com.testapi.testapi.model.Product;
import com.testapi.testapi.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    public Author addAuthor(Author author){
        return authorRepo.save(author);
    }

    public List<Author> findAll(){
        return authorRepo.findAll();
    }

    public Author findById(Long authorId){
        return authorRepo.findById(authorId).get();
    }

    public void removeAuthorById(Long authorId){
        authorRepo.deleteById(authorId);
    }

    public void addBook(Book book, Long authorId) {
        Author author = findById(authorId);
        if(author == null){
            throw new RuntimeException("Author with ID" + authorId + "not found");
        }
        author.getBooks().add(book);
        addAuthor(author);
    }
}

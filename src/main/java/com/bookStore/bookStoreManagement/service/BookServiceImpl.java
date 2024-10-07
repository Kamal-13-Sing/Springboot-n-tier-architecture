package com.bookStore.bookStoreManagement.service;

import com.bookStore.bookStoreManagement.dto.BookDto;
import com.bookStore.bookStoreManagement.helper.BookDtoHelper;
import com.bookStore.bookStoreManagement.model.Book;
import com.bookStore.bookStoreManagement.repository.BookRepository;
import com.bookStore.bookStoreManagement.util.BookConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

/*
Service Implementation Layer for all Book Related Business Logics
@Author: Kamal Thapa
 */

@Service
public class BookServiceImpl implements  BookService, BookConstants {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    // add new Book
    @Override
    public boolean addBook(String jsonData) throws JsonProcessingException {
        BookDto bookDtoObj = BookDtoHelper.convertBookDtoToBookObject(jsonData);

        Boolean status = false;
        Book book =  new Book();
                book.setId(bookDtoObj.getId());
                book.setTitle(bookDtoObj.getTitle());
                book.setAuthor(bookDtoObj.getAuthor());
                book.setPrice(bookDtoObj.getPrice());
                book.setDescription(bookDtoObj.getDescription());

       Book bookSaved = bookRepository.save(book);
       if(bookSaved != null){
           status = true;
       }

        return status;
    }

    // delete Book by Id

    public String deleteBookById(Integer bookId){

        // check id the given bookId's book is available or not

        if(bookRepository.findById(bookId).orElse(null) != null){
            bookRepository.deleteById(bookId);
            return  EMPTY;
        }else {
            return BOOK_NOT_FOUND + " with Id: "+bookId;
        }
    }

    @Override
    public List<Book> getAllBooks() {

       List<Book> bookList =bookRepository.findAll();

        return bookList;
    }

    @Override
    public Book getBookByBookId(Integer bookId) {

            return bookRepository.findById(bookId).orElse(null);

    }


}

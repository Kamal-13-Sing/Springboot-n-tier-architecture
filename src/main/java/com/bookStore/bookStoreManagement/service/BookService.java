package com.bookStore.bookStoreManagement.service;

import com.bookStore.bookStoreManagement.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public interface BookService {

    public boolean addBook(String jshonData) throws JsonProcessingException;

    public String deleteBookById(Integer bookId);

    public List<Book> getAllBooks();

    public Book getBookByBookId(Integer bookId);

    public String updateBook(String jsonData) throws JsonProcessingException;
}

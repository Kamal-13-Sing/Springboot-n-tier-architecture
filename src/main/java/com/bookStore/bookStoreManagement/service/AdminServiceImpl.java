package com.bookStore.bookStoreManagement.service;

import com.bookStore.bookStoreManagement.dto.BookDto;
import com.bookStore.bookStoreManagement.helper.BookDtoHelper;
import com.bookStore.bookStoreManagement.model.Book;
import com.bookStore.bookStoreManagement.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements  AdminService{

    private final BookRepository bookRepository;

    public AdminServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public Boolean addBook(String jsonData) throws JsonProcessingException {
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
}

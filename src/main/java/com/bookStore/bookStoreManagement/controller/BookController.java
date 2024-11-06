package com.bookStore.bookStoreManagement.controller;

import com.bookStore.bookStoreManagement.dto.BookDto;
import com.bookStore.bookStoreManagement.helper.BookHelper;
import com.bookStore.bookStoreManagement.model.Book;
import com.bookStore.bookStoreManagement.service.BookService;
import com.bookStore.bookStoreManagement.util.BookConstants;
import com.bookStore.bookStoreManagement.util.Response;
import com.bookStore.bookStoreManagement.util.ValidationConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Handel All API For Book Operations
@Author: Kamal Thapa
 */

@RestController
@RequestMapping("/api/book")
public class BookController implements BookConstants, ValidationConstants {

    // creating a logger
    Logger logger = LoggerFactory.getLogger(BookController.class);

    private  final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    //  get all book by following sagar dai coding style

//    @GetMapping("/get-all-book")
//    public ResponseEntity<Response> getAllBook(){
//
//        List<Book> book = bookService.getAllBooks();
//        return  ResponseEntity.ok(Response.builder()
//                .status(true)
//                .Message(BOOK_FOUND_SUCCESSFULLY)
//                .object(book).build());
//
//    }

    // get All Book List

    @GetMapping("/get-all-book")
    public @ResponseBody Response getAllBookList(){

        Response response = new Response();
        boolean status = false;

        List<Book> book = bookService.getAllBooks();

        if(book == null || book.isEmpty()){

            status = false;
            response.setMessage(BOOK_NOT_FOUND);
            response.setStatus(status);

        }else{

            status = true;
            response.setMessage(BOOK_FOUND_SUCCESSFULLY);
            response.setStatus(status);
            response.setObject(book);

        }

        return response;
    }


    //add new book
    @PostMapping("/add-book")
    public @ResponseBody Response addNewBook(@RequestBody String jsonData) throws JsonProcessingException {

        boolean status = false;
        Response response = new Response();

        List<String> validationResult;

        try{

           validationResult = BookHelper.validateBookJsonData(jsonData);

            if(validationResult.isEmpty()){

                status = bookService.addBook(jsonData);

                if(status){
                    status = true;
                    response.setMessage(BOOK_SAVED_SUCCESSFULLY);
                    response.setStatus(status);
                }else{
                    response.setMessage(BOOK_SAVE_FAILED);
                    response.setStatus(status);
                }

            }else{

                response.setMessage(VALIDATION_ERROR);
                response.setStatus(status);
                response.setObject(validationResult);
            }

        }catch(Exception ex){
            response.setMessage(BOOK_SAVE_FAILED);
            response.setStatus(status);
        }


        return response;
    }

    // delete book

//    @DeleteMapping("/delete-book/{bookId}")
//    public @ResponseBody Response deleteBook(@PathVariable("bookId") String bookId){

    @DeleteMapping("/delete-book")
    public @ResponseBody Response deleteBook(@RequestParam("bookId") String bookId){

    Response response = new Response();
        boolean status = false;

        List<String> validationResult;

        validationResult = BookHelper.validateBookDeletion(bookId);

        if(validationResult.isEmpty()){

            String isDeleted = bookService.deleteBookById(Integer.parseInt(bookId));

            if(isDeleted.isBlank()){

                status = true;
                response.setStatus(status);
                response.setMessage(BOOK_DELETED_SUCCESSFULLY);

            }else{

                status = false;
                response.setStatus(status);
                response.setMessage(BOOK_DELETION_FAILED);
                response.setObject(isDeleted);
            }

        }else{

            status = false;
            response.setStatus(status);
            response.setMessage(VALIDATION_ERROR);
            response.setObject(validationResult);
        }

        return response;
    }



    // get book by book id

    @GetMapping("/get-book-by-id")
    public @ResponseBody Response getBookByBookId(@RequestParam("bookId") Integer bookId ){

        Response response = new Response();
        boolean status = false;

        if(!String.valueOf(bookId).isBlank()){

            Book getByBookId = bookService.getBookByBookId(bookId);

            if(getByBookId != null){
                status = true;
                response.setStatus(status);
                response.setMessage(BOOK_FOUND_SUCCESSFULLY);
                response.setObject(getByBookId);
            }else{
                response.setStatus(status);
                response.setMessage(BOOK_NOT_FOUND);
                response.setObject(getByBookId);
            }
        }else{
            response.setMessage(BOOK_ID_IS_REQUIRED);
            response.setStatus(status);

        }
        return  response;
    }


    // update book

    @PutMapping("/update-book")
    public @ResponseBody Response updateBook(@RequestBody String jsonData) throws JsonProcessingException {

        Response response = new Response();
        boolean status = false;
        List<String> validationResult;

       validationResult = BookHelper.validateBookJsonData(jsonData);
        BookDto bookDto = BookHelper.convertBookDtoToBookObject(jsonData);

        Book getByBookId = bookService.getBookByBookId(bookDto.getId());


       if(validationResult.isEmpty()){

           if(getByBookId != null){

               String update = bookService.updateBook(jsonData);
               status = true;
               response.setObject(jsonData);
               response.setMessage(BOOK_UPDATED_SUCCESSFULLY);

           }else{

               response.setStatus(status);
               response.setMessage(BOOK_NOT_FOUND);
               response.setObject(getByBookId);
           }

       }else{

           response.setStatus(status);
           response.setMessage(BOOK_UPDATE_FAILED);
           response.setObject(validationResult);
       }

        return  response;

    }

}

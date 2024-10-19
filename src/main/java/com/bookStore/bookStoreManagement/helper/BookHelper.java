package com.bookStore.bookStoreManagement.helper;

import com.bookStore.bookStoreManagement.dto.BookDto;
import com.bookStore.bookStoreManagement.service.BookService;
import com.bookStore.bookStoreManagement.util.BookConstants;
import com.bookStore.bookStoreManagement.util.ValidationConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class BookHelper implements BookConstants, ValidationConstants {

    private final BookService bookService;

    public BookHelper(BookService bookService) {
        this.bookService = bookService;
    }

    // Book Json to Book Dto Object

    public static BookDto convertBookDtoToBookObject(String BookJson) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(BookJson, BookDto.class);
    }


    // book Dto Validation

    public static List<String> validateBookJsonData(String jsonData){

        List<String> validationMessage =  new ArrayList<>();

        try{

            BookDto bookDtoObj = BookHelper.convertBookDtoToBookObject(jsonData);


            /* book id validation condition */
            String bookId = String.valueOf(bookDtoObj.getBookId());
            if(bookId == null || bookId.isEmpty()){
                validationMessage.add(BOOK_ID_IS_REQUIRED);
            }

            /* book Title validation condition */
            String bookTitle = String.valueOf(bookDtoObj.getTitle());
            if(bookTitle == null || bookTitle.isEmpty()){
                validationMessage.add(BOOK_TITLE_IS_REQUIRED);
            }

            /* book author validation condition */
            String bookAuthor = String.valueOf(bookDtoObj.getAuthor());
            if(bookAuthor == null || bookAuthor.isEmpty()){
                validationMessage.add(BOOK_AUTHOR_IS_REQUIRED);
            }

            /* book price validation condition */
            String bookPrice = String.valueOf(bookDtoObj.getPrice());
            if(bookPrice == null || bookPrice.isEmpty()){
                validationMessage.add(BOOK_PRICE_IS_REQUIRED);
            }

            /* book description validation condition */
            String bookDescription = String.valueOf(bookDtoObj.getDescription());
            if(bookDescription == null || bookDescription.isEmpty()){
                validationMessage.add(BOOK_DESCRIPTION_IS_REQUIRED);
            }

        }catch(Exception ex){
            validationMessage.add(SPECIFIED_KEY_NOT_FOUND + ex.toString());
            System.out.println(SPECIFIED_KEY_NOT_FOUND + ex.toString());
        }

        return validationMessage;
    }

    // book deletion Helper

    // validate given integer
    public static List<String> validateBookDeletion(String bookId){
        List<String> validationMessage = new ArrayList<>();

        try{
            if(bookId == null || bookId.isBlank()){
                validationMessage.add(BOOK_ID_IS_REQUIRED);
            }

        }catch (Exception ex){
            validationMessage.add(BOOK_ID_IS_REQUIRED + ex.toString());
        }

        return validationMessage;
    }

    // book update helper

    public  List<String> validateUpdateBook(String jsonData) throws JsonProcessingException {

        List<String> validationMessage = new ArrayList<>();
        BookDto bookDto =  BookHelper.convertBookDtoToBookObject(jsonData);

        if (bookService.getBookByBookId(bookDto.getId()) == null) {

            validationMessage.add(BOOK_NOT_FOUND);
        }

        return validationMessage;

    }
}

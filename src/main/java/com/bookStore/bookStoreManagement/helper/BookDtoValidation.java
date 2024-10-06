package com.bookStore.bookStoreManagement.helper;

import com.bookStore.bookStoreManagement.dto.BookDto;
import com.bookStore.bookStoreManagement.util.ValidationConstants;

import java.util.ArrayList;
import java.util.List;


public class BookDtoValidation implements ValidationConstants {

    public static List<String> validateBookSaving(String jsonData){

        List<String> validationMessage =  new ArrayList<>();

        try{

            BookDto bookDtoObj = BookDtoHelper.convertBookDtoToBookObject(jsonData);


            /* book id validation condition */
            String bookId = String.valueOf(bookDtoObj.getId());
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

}

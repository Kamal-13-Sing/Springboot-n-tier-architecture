package com.bookStore.bookStoreManagement.helper;

import com.bookStore.bookStoreManagement.util.BookConstants;
import com.bookStore.bookStoreManagement.util.ValidationConstants;

import java.util.ArrayList;
import java.util.List;

public class BookDeletionHelper implements BookConstants, ValidationConstants {

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
}

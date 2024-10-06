package com.bookStore.bookStoreManagement.controller;

import com.bookStore.bookStoreManagement.helper.BookDtoValidation;
import com.bookStore.bookStoreManagement.service.AdminService;
import com.bookStore.bookStoreManagement.util.BookConstants;
import com.bookStore.bookStoreManagement.util.Response;
import com.bookStore.bookStoreManagement.util.ValidationConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
Handel All API For Admin Operations
@Author: Kamal Thapa
 */

@RestController
@RequestMapping("/api/admin")
public class AdminController implements BookConstants, ValidationConstants {

    private  final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //add new book
    @PostMapping("/add-book")
    public @ResponseBody Response addNewBook(@RequestBody String jsonData) throws JsonProcessingException {

        boolean status = false;
        Response response = new Response();

        List<String> validationResult;

        try{

           validationResult = BookDtoValidation.validateBookSaving(jsonData);

            if(validationResult.isEmpty()){

                status = adminService.addBook(jsonData);

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

}

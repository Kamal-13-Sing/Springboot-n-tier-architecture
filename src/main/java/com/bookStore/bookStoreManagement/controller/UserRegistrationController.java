package com.bookStore.bookStoreManagement.controller;


import com.bookStore.bookStoreManagement.helper.UserHelper;
import com.bookStore.bookStoreManagement.model.User;
import com.bookStore.bookStoreManagement.service.UserService;
import com.bookStore.bookStoreManagement.util.Response;
import com.bookStore.bookStoreManagement.util.UserConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRegistrationController implements UserConstants {

    private  final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    // register new customer

    @PostMapping("/register")
    public @ResponseBody Response registerCustomer(@RequestBody String jshonData) throws JsonProcessingException {
        List<String> validationResult;
        boolean status = false;
        Response response = new Response();



        validationResult = UserHelper.validateUserJsonData(jshonData);
        User userObj = UserHelper.convertJsonToObject(jshonData);

        if(validationResult.isEmpty()){

        UserHelper.validateUserJsonData(jshonData);

            boolean isRegistered = false;

           isRegistered = userService.registerUser(userObj);

           if(isRegistered){

               status = true;
               response.setStatus(status);
               response.setMessage(USER_REGISTERED_SUCCESSFULLY);
           }else {

               response.setStatus(status);
               response.setMessage(USER_REGISTRATION_FAILED);
           }

        }else{

            response.setStatus(status);
            response.setMessage(VALIDATION_ERROR);
            response.setObject(validationResult);
        }

        return  response;

    }
}

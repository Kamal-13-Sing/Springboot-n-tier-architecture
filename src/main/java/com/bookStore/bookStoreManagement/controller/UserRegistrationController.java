package com.bookStore.bookStoreManagement.controller;


import com.bookStore.bookStoreManagement.helper.UserHelper;
import com.bookStore.bookStoreManagement.util.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class UserRegistrationController {

    // register new customer

    @PostMapping("/register")
    public @ResponseBody Response registerCustomer(@RequestBody String jshonData){

        Response response = new Response();
        List<String> validationResult;

        UserHelper.validateUserJsonData(jshonData);
// testing

        return  null;

    }
}

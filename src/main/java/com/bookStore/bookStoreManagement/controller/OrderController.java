package com.bookStore.bookStoreManagement.controller;

import com.bookStore.bookStoreManagement.helper.OrderHelper;
import com.bookStore.bookStoreManagement.util.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/order")
public class OrderController {

    // post order

    @PostMapping("/saveorderdetails")
    public @ResponseBody Response getOrderDetails(@RequestBody String jsonData) {

        Response response = new Response();
        boolean status = false;
        List<String> validationResult;


        OrderHelper.validateOrderDetails(jsonData);


        return null;
    }


}

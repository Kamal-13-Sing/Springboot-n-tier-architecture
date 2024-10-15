package com.bookStore.bookStoreManagement.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class OrderHelper {

    // validate order details

    public static List<String> validateOrderDetails (String jsonData)  {


        List<String> validationMessage = new ArrayList<>();
        try{

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode orderedBook = objectMapper.readTree(jsonData).get("orderedBook");

            System.out.println(orderedBook);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return  validationMessage;

    }



}

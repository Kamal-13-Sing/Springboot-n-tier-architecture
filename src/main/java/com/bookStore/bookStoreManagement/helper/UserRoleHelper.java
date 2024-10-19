package com.bookStore.bookStoreManagement.helper;


import com.bookStore.bookStoreManagement.model.UserRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserRoleHelper {

    //json Data to UserRole Object

    public static UserRole convertJsonToObject(String jsonData) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

       UserRole userRole = objectMapper.readValue(jsonData, UserRole.class);

        return userRole;
    }
}

package com.bookStore.bookStoreManagement.helper;

import com.bookStore.bookStoreManagement.model.User;
import com.bookStore.bookStoreManagement.util.UserConstants;
import com.bookStore.bookStoreManagement.util.ValidationConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class UserHelper implements UserConstants, ValidationConstants {
    
    
    // User Json to obect mapping
    
    public static User convertJsonToObject(String jsonData) throws JsonProcessingException {
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        return objectMapper.readValue(jsonData, User.class);
    }

    // User json Validation

    public static List<String> validateUserJsonData(String jsonData){

        List<String> validationMessage =  new ArrayList<>();

        try{
            
            User userObj = convertJsonToObject(jsonData);
            
            /* userid validation condition */
            String userId = String.valueOf(userObj.getUserId());
            if(userId == null || userId.isEmpty()){
                validationMessage.add(USER_ID_IS_REQUIRED);
            }

            /* user fullname validation condition */
            String userFullname = String.valueOf(userObj.getFullName());
            if(userFullname == null || userFullname.isEmpty()){
                validationMessage.add(USER_FULLNAME_IS_REQUIRED);
            }

            /* username validation condition */
            String userName = String.valueOf(userObj.getUserName());
            if(userName == null || userName.isEmpty()){
                validationMessage.add(USERNAME_IS_REQUIRED);
            }

            /* password validation condition */
            String userPassword = String.valueOf(userObj.getPassword());
            if(userPassword == null || userPassword.isEmpty()){
                validationMessage.add(USER_PASSWORD_IS_REQUIRED);
            }

            /* contact validation condition */
            String userContact = String.valueOf(userObj.getContact());
            if(userContact == null || userContact.isEmpty()){
                validationMessage.add(USER_CONTACT_IS_REQUIRED);
            }

        }catch(Exception ex){
            validationMessage.add(SPECIFIED_KEY_NOT_FOUND + ex.toString());
            System.out.println(SPECIFIED_KEY_NOT_FOUND + ex.toString());
        }

        return validationMessage;
    }
    
}

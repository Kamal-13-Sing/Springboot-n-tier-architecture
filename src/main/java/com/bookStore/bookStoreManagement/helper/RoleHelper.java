package com.bookStore.bookStoreManagement.helper;

import com.bookStore.bookStoreManagement.model.Role;
import com.bookStore.bookStoreManagement.util.RoleConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class RoleHelper implements RoleConstants {

    //json Data to Role Object

    public static Role convertJsonToObject(String jsonData) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        Role role = objectMapper.readValue(jsonData, Role.class);
        return role;
    }
    
    // validate Role of the user
    
    public static List<String> roleValidation(String jsonData) throws JsonProcessingException {
        
        Role roleObj = convertJsonToObject(jsonData);

        List<String> validationMessage = new ArrayList<>();
        
        /* roleId validation condition */
        String roleId = String.valueOf(roleObj.getRoleId());
        if(roleId == null || roleId.isEmpty()){
            validationMessage.add(ROLE_ID_IS_REQUIRED);
        }

        /* role name validation condition */
        String roleName = String.valueOf(roleObj.getName());
        if(roleName == null || roleName.isEmpty()) {
            validationMessage.add(ROLE_NAME_IS_REQUIRED);
        }

        /* role description validation condition */
        String roleDescription = String.valueOf(roleObj.getDescription());
        if(roleDescription == null || roleDescription.isEmpty()){
            validationMessage.add(ROLE_DESCRIPTION_IS_REQUIRED);
        }
        
        return validationMessage;
    }

}

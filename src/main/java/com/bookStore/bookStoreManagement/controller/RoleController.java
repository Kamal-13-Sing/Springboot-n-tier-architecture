package com.bookStore.bookStoreManagement.controller;

import com.bookStore.bookStoreManagement.helper.RoleHelper;
import com.bookStore.bookStoreManagement.service.RoleService;
import com.bookStore.bookStoreManagement.util.Response;
import com.bookStore.bookStoreManagement.util.RoleConstants;
import com.bookStore.bookStoreManagement.util.ValidationConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController implements RoleConstants, ValidationConstants {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    // Create a new role

    @PostMapping("/create-role")
    public @ResponseBody Response createRole(@RequestBody String jsonData) throws JsonProcessingException {

        Response response = new Response();
        List<String> validationResult;
        boolean status = false;

        validationResult = RoleHelper.roleValidation(jsonData);

        if(validationResult.isEmpty()){

            boolean isRoleCreated = roleService.createRole(jsonData);
            if(isRoleCreated){
                status = true;
                response.setMessage(ROLE_CREATED_SUCCESSFULLY);
                response.setStatus(status);
            }else{
                response.setMessage(FAILED_TO_CREATE_ROLE);
                response.setStatus(status);
            }

        }else {
            response.setStatus(status);
            response.setMessage(VALIDATION_ERROR);
            response.setObject(validationResult);
        }
        return  response;
    }

}

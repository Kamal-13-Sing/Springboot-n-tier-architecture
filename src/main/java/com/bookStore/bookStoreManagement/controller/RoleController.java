package com.bookStore.bookStoreManagement.controller;

import com.bookStore.bookStoreManagement.helper.RoleHelper;
import com.bookStore.bookStoreManagement.helper.UserRoleHelper;
import com.bookStore.bookStoreManagement.model.Role;
import com.bookStore.bookStoreManagement.model.UserRole;
import com.bookStore.bookStoreManagement.service.RoleService;
import com.bookStore.bookStoreManagement.util.Response;
import com.bookStore.bookStoreManagement.util.RoleConstants;
import com.bookStore.bookStoreManagement.util.ValidationConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController implements RoleConstants, ValidationConstants {

    private final RoleService roleService;


    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    private Logger logger = LoggerFactory.getLogger(RoleController.class);

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

    // asign a role to the user

    @PostMapping("/save-role")
    public @ResponseBody Response roleAssigning(@RequestParam("jsonData") String jsonData){

        Response response = new Response();
        boolean status = false;

        try{

         //   UserRole userRole = UserRoleHelper.convertJsonToObject(jsonData);

            boolean isRoleSaved = roleService.saveUserRole(jsonData);

            if(isRoleSaved){
                status  = true;
                response.setMessage(SAVE_SUCCESSFULLY);
                response.setStatus(status);
            }else{
                response.setStatus(status);
                response.setMessage(ERROR_UNSUCCESS);
            }

        }catch (Exception ex){
            if(logger.isDebugEnabled()){
                logger.debug("exception occured: "+ex.getLocalizedMessage());
            }

            response.setStatus(status);
            response.setMessage(ex.getLocalizedMessage());
        }

        return response;
    }

}

package com.bookStore.bookStoreManagement.controller;


import static com.bookStore.bookStoreManagement.util.ValidationConstants.SAVE_SUCCESSFULLY;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.bookStore.bookStoreManagement.service.RoleService;
import com.bookStore.bookStoreManagement.util.Response;
import com.bookStore.bookStoreManagement.util.RoleConstants;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

@WebMvcTest(RoleController.class)
@ExtendWith(MockitoExtension.class)
public class RoleControllerTest implements RoleConstants {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    @Autowired
    private ObjectMapper objectMapper;

    // Test for createRole endpoint
    @Test
    void testCreateRole_Success() throws Exception {
        String jsonData = "{\n" +
                "    \"roleId\": \"3\",\n" +
                "    \"name\": \"ADMIN2\",\n" +
                "    \"description\": \"admin role2\"\n" +
                "  }";
        Response expectedResponse = new Response(true, ROLE_CREATED_SUCCESSFULLY, null);

        when(roleService.createRole(jsonData)).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(post("/api/role/create-role")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        Response actualResponse = objectMapper.readValue(content, Response.class);
        assertThat(actualResponse.getMessage()).isEqualTo(expectedResponse.getMessage());
//        assertThat(actualResponse.getMessage()).isEqualTo(ROLE_CREATED_SUCCESSFULLY);

    }

    /*
    // roleAssigning
     */


    // start

    @Test
    public void testroleAssigning() throws Exception {

        String jsonData = "{\n" +
                "    \"roleId\": \"1\",\n" +
                "    \"userId\": \"SAP123\"\n" +
                "  }";

        Response expectedResponse = new Response(true, SAVE_SUCCESSFULLY,null);

         when(roleService.saveUserRole(jsonData)).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(
                post("/api/role/save-role")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("jsonData", jsonData))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();


        Response actualResponse = objectMapper.readValue(content, Response.class);


        assertThat(actualResponse.getMessage()).isEqualTo(expectedResponse.getMessage());


    }

//    @Test
//    public void testRoleAssigningSuccess() throws Exception {
//        String jsonData = "{\n" +
//                "    \"roleId\": \"1\",\n" +
//                "    \"userId\": \"SAP123\"\n" +
//                "  }";
//
//        // Expected response when save is successful
//        Response expectedResponse = new Response(true, SAVE_SUCCESSFULLY, null);
//        UserRole userRole = new UserRole(1,"2", "SAP123");  // Adjust this to match UserRole fields
//
//        // Mocking
//        when(UserRoleHelper.convertJsonToObject(jsonData)).thenReturn(userRole);
//        when(roleService.saveUserRole(userRole)).thenReturn(true);
//
//        MvcResult mvcResult = mockMvc.perform(
//                        post("/api/role/save-role")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .param("jsonData", jsonData))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String content = mvcResult.getResponse().getContentAsString();
//        Response actualResponse = objectMapper.readValue(content, Response.class);
//
//        // Verifying response fields
//        assertThat(actualResponse.getMessage()).isEqualTo(expectedResponse.getMessage());
//
//        // Verify that the saveUserRole method was called
//        verify(roleService).saveUserRole(userRole);
//    }

    //end

//    @Test
//    void testCreateRole_ValidationError() throws Exception {
//        String invalidJsonData = "{\"roleName\": \"\"}";
//        Response expectedResponse = new Response(false, VALIDATION_ERROR, Collections.singletonList("roleName is required"));
//
//        when(roleService.createRole(invalidJsonData)).thenReturn(false);
//
//        MvcResult mvcResult = mockMvc.perform(post("/api/role/create-role")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(invalidJsonData))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String content = mvcResult.getResponse().getContentAsString();
//        Response actualResponse = objectMapper.readValue(content, Response.class);
//
//        //assertThat(actualResponse.isStatus()).isFalse();
//        assertThat(actualResponse.getMessage()).isEqualTo(VALIDATION_ERROR);
//    }
//
//    // Test for roleAssigning endpoint
//    @Test
//    void testRoleAssigning_Success() throws Exception {
//        String jsonData = "{\"userId\": 1, \"roleId\": 2}";
//        UserRole userRole = new UserRole(1,"1", "2");
//        Response expectedResponse = new Response(true, SAVE_SUCCESSFULLY, null);
//
//        when(roleService.saveUserRole(userRole)).thenReturn(true);
//
//        MvcResult mvcResult = mockMvc.perform(post("/api/role/save-role")
//                        .param("jsonData", jsonData)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String content = mvcResult.getResponse().getContentAsString();
//        Response actualResponse = objectMapper.readValue(content, Response.class);
//
//       // assertThat(actualResponse.isStatus()).isTrue();
//        assertThat(actualResponse.getMessage()).isEqualTo(SAVE_SUCCESSFULLY);
//    }
//
//    @Test
//    void testRoleAssigning_Error() throws Exception {
//        String invalidJsonData = "{\"userId\": null, \"roleId\": 2}";
//        Response expectedResponse = new Response(false, ERROR_UNSUCCESS, null);
//
//        when(roleService.saveUserRole(null)).thenReturn(false);
//
//        MvcResult mvcResult = mockMvc.perform(post("/api/role/save-role")
//                        .param("jsonData", invalidJsonData)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String content = mvcResult.getResponse().getContentAsString();
//        Response actualResponse = objectMapper.readValue(content, Response.class);
//
//      //  assertThat(actualResponse.isStatus()).isFalse();
//        assertThat(actualResponse.getMessage()).isEqualTo(ERROR_UNSUCCESS);
//    }
}

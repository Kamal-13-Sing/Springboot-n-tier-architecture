package com.bookStore.bookStoreManagement.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.bookStore.bookStoreManagement.dto.OrderDetailsDto;
import com.bookStore.bookStoreManagement.service.OrdersService;
import com.bookStore.bookStoreManagement.util.Response;
import com.bookStore.bookStoreManagement.util.ValidationConstants;
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

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@ExtendWith(MockitoExtension.class)
public class OrderControllerTest implements ValidationConstants {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderController orderController;

    @MockBean
    private OrdersService ordersService;

    @Autowired
    private ObjectMapper objectMapper;


    // tet get order details

    @Test
    public void testGetOrderDetails() throws Exception {

        String orderId = "ORDER202";

        OrderDetailsDto mockOrder = new OrderDetailsDto("ORDER202","HPE101",100,null);

        Response expectedResponse = new Response(true,DATA_FOUND_SUCCESSFULLY,mockOrder);

        when(ordersService.getOrderDetailsByOrderId(orderId)).thenReturn(mockOrder);

        MvcResult mvcResult = mockMvc.perform(get("/api/order/getorderdetailsbyorderid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("orderId", orderId))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Response actualResponse = objectMapper.readValue(content, Response.class);

//        System.out.println("OrderDtoObj: "+mockOrder.getBookId());
//        System.out.println("ResponseObj: "+actualResponse.getObject());
        assertThat(actualResponse.getStatus()).isEqualTo(expectedResponse.getStatus());
        assertThat(actualResponse.getMessage()).isEqualTo(expectedResponse.getMessage());

    }

}

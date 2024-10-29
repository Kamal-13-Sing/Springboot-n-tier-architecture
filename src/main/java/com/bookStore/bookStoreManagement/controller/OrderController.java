package com.bookStore.bookStoreManagement.controller;

import com.bookStore.bookStoreManagement.dto.OrderAndUserDetailDto;
import com.bookStore.bookStoreManagement.dto.OrderDetailsDto;
import com.bookStore.bookStoreManagement.dto.UserDetailDto;
import com.bookStore.bookStoreManagement.helper.OrderHelper;
import com.bookStore.bookStoreManagement.helper.OrderValidation;
import com.bookStore.bookStoreManagement.model.OrderedBook;
import com.bookStore.bookStoreManagement.model.Orders;
import com.bookStore.bookStoreManagement.service.OrdersService;
import com.bookStore.bookStoreManagement.util.Response;
import com.bookStore.bookStoreManagement.util.ValidationConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/order")
public class OrderController implements ValidationConstants {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrdersService ordersService;

    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    // post order

    @PostMapping("/saveorderdetails")
    public @ResponseBody Response saveOrderDetails(@RequestParam("jsonData") String jsonData) throws JsonProcessingException {

        Response response = new Response();
        boolean status = false;
        List<String> validationResult;
        System.out.println("save order: "+jsonData);

       try{

         //  validationResult = OrderValidation.validateOrderDetails(jsonData);

           // generate the order id
           String orderId = OrderHelper.generateOrderId();

           OrderedBook orderedBookObj = OrderHelper.processOrderedBook(jsonData,orderId);
           Orders ordersObj =  OrderHelper.processOrdersInfo(jsonData,orderId);

          boolean isOrdersSaved =  ordersService.saveOrderesInfo(orderedBookObj, ordersObj);

          if(isOrdersSaved){
              status = true;
              response.setMessage(SAVE_SUCCESSFULLY);
              response.setStatus(status);

          }else{
              response.setStatus(status);
              response.setMessage(ERROR_UNSUCCESS);
          }
       }catch (Exception ex){

           logger.error("Error occured : "+ex.getLocalizedMessage());
           response.setStatus(status);
           response.setMessage(ERROR_UNSUCCESS);
       }

        return response;
    }

    // get order details

    @GetMapping("/getorderdetailsbyorderid")
    public @ResponseBody Response getOrderDetailsByOrderId(@RequestParam("orderId") String orderId){

        Response response = new Response();
        boolean status = false;
        List<String> validationResult;

        try{

           OrderDetailsDto orderDetailsDto = ordersService.getOrderDetailsByOrderId(orderId);

            if(orderDetailsDto != null){
                status = true;
                response.setMessage(DATA_FOUND_SUCCESSFULLY);
                response.setStatus(status);
                response.setObject(orderDetailsDto);
            }else{
                response.setStatus(status);
                response.setMessage(DATA_NOT_FOUND);
            }


        }catch (Exception ex){
            if(logger.isDebugEnabled()){
                logger.debug(OrderController.class + ex.getLocalizedMessage());
            }
        }

        return response;
    }

    // get user and their ordered details

    @GetMapping("/get-order-details")
    public @ResponseBody Response getOrderDetails(){

        Response response = new Response();
        boolean status = false;

        try{

            List<UserDetailDto> userDetailDtos =   ordersService.getOrderDetails();

            response.setObject(userDetailDtos);


        }catch (Exception ex){
            if(logger.isDebugEnabled()){
                logger.debug("Exception Occured: "+ex.getLocalizedMessage());
            }
        }

        return  response;
    }

}

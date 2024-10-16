package com.bookStore.bookStoreManagement.helper;

import com.bookStore.bookStoreManagement.model.OrderedBook;
import com.bookStore.bookStoreManagement.model.Orders;
import com.bookStore.bookStoreManagement.util.BookStoreMgmtConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderHelper implements BookStoreMgmtConstants {

    private  final Logger logger = LoggerFactory.getLogger(OrderHelper.class);

    // process ordered book json data
    public static OrderedBook processOrderedBook (String jsonData, String orderId) throws JsonProcessingException {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode orderedBookJson = objectMapper.readTree(jsonData).get(ORDERED_BOOK_INFO);

            System.out.println(orderedBookJson);

            String bookId = orderedBookJson.get(BOOK_ID).asText();
            double bookPrice = orderedBookJson.get(BOOK_PRICE).asDouble();

            OrderedBook orderedBookObj = new OrderedBook(
                    null,
                    orderId,
                    bookId,
                    bookPrice
            );

        return orderedBookObj;
    }

    // process orders

    public static Orders processOrdersInfo(String jsonData, String orderId) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode ordersJson = objectMapper.readTree(jsonData).get(ORDERS_INFO);

        double totalAmount = ordersJson.get(TOTAL_AMOUNT).asDouble();
        String userId = ordersJson.get(USER_ID).asText();

        Orders orders = new Orders(
                null,
                orderId,
                userId,
                totalAmount
        );

        return orders;
    }


    // Method to generate the unique order ID using timestamp
    public static String generateOrderId() {
        // Create a date formatter to include the year, month, day, hour, minute, second
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

        // Get the current date and time
        String timestamp = formatter.format(new Date());

        // Combine with a prefix for order
        return "ORDER" + timestamp;
    }



}

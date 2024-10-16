package com.bookStore.bookStoreManagement.service;

import com.bookStore.bookStoreManagement.dto.OrderDetailsDto;
import com.bookStore.bookStoreManagement.model.OrderedBook;
import com.bookStore.bookStoreManagement.model.Orders;


import java.util.List;


public interface OrdersService {

    boolean saveOrderesInfo(OrderedBook orderedBookObject, Orders ordesObject);

    public List<OrderDetailsDto> getOrderDetailsByOrderId(String orderId);

}

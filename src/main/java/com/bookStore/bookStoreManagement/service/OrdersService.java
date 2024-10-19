package com.bookStore.bookStoreManagement.service;

import com.bookStore.bookStoreManagement.dto.OrderAndUserDetailDto;
import com.bookStore.bookStoreManagement.dto.OrderDetailsDto;
import com.bookStore.bookStoreManagement.model.OrderedBook;
import com.bookStore.bookStoreManagement.model.Orders;


import java.util.List;
import java.util.Map;

public interface OrdersService {

    boolean saveOrderesInfo(OrderedBook orderedBookObject, Orders ordesObject);

    public List<OrderDetailsDto> getOrderDetailsByOrderId(String orderId);

    public List<OrderAndUserDetailDto> getOrderDetails();

}

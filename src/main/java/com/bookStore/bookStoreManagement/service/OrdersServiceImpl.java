package com.bookStore.bookStoreManagement.service;

import com.bookStore.bookStoreManagement.dto.OrderAndUserDetailDto;
import com.bookStore.bookStoreManagement.dto.OrderDetailsDto;
import com.bookStore.bookStoreManagement.dto.UserDetailDto;
import com.bookStore.bookStoreManagement.model.OrderedBook;
import com.bookStore.bookStoreManagement.model.Orders;
import com.bookStore.bookStoreManagement.repository.OrderedBookRepository;
import com.bookStore.bookStoreManagement.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrderedBookRepository orderedBookRepository;
    private  final OrdersRepository ordersRepository;

    public OrdersServiceImpl(OrderedBookRepository orderedBookRepository, OrdersRepository ordersRepository) {
        this.orderedBookRepository = orderedBookRepository;
        this.ordersRepository = ordersRepository;
    }

    @Override
    public boolean saveOrderesInfo(OrderedBook orderedBookObject, Orders ordesObject) {

        orderedBookRepository.save(orderedBookObject);
        ordersRepository.save(ordesObject);

        return true;
    }

    @Override
    public List<OrderDetailsDto> getOrderDetailsByOrderId(String orderId) {

        System.out.println("1"+orderId);
        //List<Map<String, Object>> orderDetailsByOrderId = orderedBookRepository.getOrderDetailsByOrderId(orderId);

      //  List<Object> orderDetailsByOrderId = orderedBookRepository.getOrderDetailsByOrderId(orderId);


        List<OrderDetailsDto> orderDetailsByOrderId = orderedBookRepository.getOrderDetailsByOrderId(orderId);
        if(orderDetailsByOrderId == null){
            System.out.println("returning null: ");
        }

        System.out.println("2");
        return orderDetailsByOrderId;
//        return null;
    }

    @Override
    public List<OrderAndUserDetailDto> getOrderDetails() {



        // fetch all the userId list who have order
        List<String> orderedUserIdList = orderedBookRepository.getOrderedUserIdList();


        List<OrderAndUserDetailDto> orderAndUserDetailDtos = new ArrayList<>();

        List<UserDetailDto> userDetailDtoList = new ArrayList<>();
        List<OrderDetailsDto> orderDetailsDtoList = new ArrayList<>();

        OrderAndUserDetailDto orderAndUserDetailDto =  new OrderAndUserDetailDto();
        for(String userId : orderedUserIdList){
            System.out.println("userId: "+userId);

          UserDetailDto userDetailDto =  orderedBookRepository.getOrderedUserDetailsByUserId(userId);
          userDetailDtoList.add(userDetailDto);

          List<OrderDetailsDto> orderDetailsDto = orderedBookRepository.getOrderDetailsByUserId(userId);

            orderDetailsDtoList.add((OrderDetailsDto) orderDetailsDto);

        }

       orderAndUserDetailDto.setUserDetailDtoList(userDetailDtoList);
      //  orderAndUserDetailDto.setOrderDetailsDto(orderDetailsDto);



        return orderAndUserDetailDtos;
    }


}

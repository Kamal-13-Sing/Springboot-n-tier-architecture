package com.bookStore.bookStoreManagement.service;

import com.bookStore.bookStoreManagement.dto.BookInfoDto;
import com.bookStore.bookStoreManagement.dto.OrderAndUserDetailDto;
import com.bookStore.bookStoreManagement.dto.OrderDetailsDto;
import com.bookStore.bookStoreManagement.dto.UserDetailDto;
import com.bookStore.bookStoreManagement.model.OrderedBook;
import com.bookStore.bookStoreManagement.model.Orders;
import com.bookStore.bookStoreManagement.repository.OrderedBookRepository;
import com.bookStore.bookStoreManagement.repository.OrdersRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    @Override
    public boolean saveOrderesInfo(OrderedBook orderedBookObject, Orders ordesObject) {

        orderedBookRepository.save(orderedBookObject);
        orderedBookRepository.updateBookQuentityAfterOrderdPlaced(orderedBookObject.getQuantity(), orderedBookObject.getBookId());
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
    public List<UserDetailDto> getOrderDetails() {



        // fetch all the userId list who have order
        List<String> orderedUserIdList = orderedBookRepository.getOrderedUserIdList();


        List<UserDetailDto> userDetailDtoList = new ArrayList<>();

        OrderAndUserDetailDto orderAndUserDetailDto =  new OrderAndUserDetailDto();

        BookInfoDto bookInfoDto =  new BookInfoDto();

        for(String userId : orderedUserIdList){

            System.out.println("userId: "+userId);

          UserDetailDto userDetailDto =  orderedBookRepository.getOrderedUserDetailsByUserId(userId);
          userDetailDtoList.add(userDetailDto);

          List<OrderDetailsDto> orderDetailsDtos = orderedBookRepository.getOrderDetailsByUserId(userId);


         for(OrderDetailsDto OrderDetailsDtoObj : orderDetailsDtos){

             bookInfoDto = orderedBookRepository.getBookInfoByBookId(OrderDetailsDtoObj.getBookId());
             OrderDetailsDtoObj.setBookDetails(bookInfoDto);
         }


            userDetailDto.setOrder(orderDetailsDtos);

        }
       // orderAndUserDetailDto.setUserDetailDtoList(userDetailDtoList);

        return userDetailDtoList;
    }


}

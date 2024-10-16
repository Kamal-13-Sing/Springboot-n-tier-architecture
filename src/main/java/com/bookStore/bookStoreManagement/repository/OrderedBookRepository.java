package com.bookStore.bookStoreManagement.repository;

import com.bookStore.bookStoreManagement.dto.OrderDetailsDto;
import com.bookStore.bookStoreManagement.model.OrderedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface OrderedBookRepository extends JpaRepository<OrderedBook, Integer> {

// fetching data without making DTO
//    @Query(value = "select a.order_id, a.user_id, b.book_id, b.book_price, a.total_amount from orders as a\n" +
//            "inner join ordered_book as b on a.order_id = b.order_id where a.order_id =?1 ", nativeQuery = true)
//
//    List<Map<String, Object>> getOrderDetailsByOrderId(String orderId);

    // jpql
    @Query(value = "select new com.bookStore.bookStoreManagement.dto.OrderDetailsDto(a.orderId, a.userId, b.bookId, \n" +
            "b.bookPrice, a.totalAmount)\n" +
            "FROM Orders AS a\n" +
            "INNER JOIN OrderedBook AS b ON a.orderId = b.orderId \n" +
            "WHERE a.orderId =?1")
    List<OrderDetailsDto> getOrderDetailsByOrderId(String orderId);

//





}

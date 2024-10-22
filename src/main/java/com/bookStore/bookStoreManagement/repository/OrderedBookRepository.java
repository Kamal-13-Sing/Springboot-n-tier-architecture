package com.bookStore.bookStoreManagement.repository;

import com.bookStore.bookStoreManagement.dto.BookInfoDto;
import com.bookStore.bookStoreManagement.dto.OrderDetailsDto;
import com.bookStore.bookStoreManagement.dto.UserDetailDto;
import com.bookStore.bookStoreManagement.model.OrderedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    @Query(value = "select new com.bookStore.bookStoreManagement.dto.OrderDetailsDto(a.orderId, b.bookId, \n" +
            "b.bookPrice)\n" +
            "FROM Orders AS a\n" +
            "INNER JOIN OrderedBook AS b ON a.orderId = b.orderId \n" +
            "WHERE a.orderId =?1")
    List<OrderDetailsDto> getOrderDetailsByOrderId(String orderId);


// fetch the list of user who ordered book with native query

//    @Query(value = "select a.user_id from user as a\n" +
//            "inner join orders as b on a.user_id = b.user_id\n" +
//            "inner join ordered_book as c on b.order_id = c.order_id group by a.user_id", nativeQuery = true)
//    List<Map<String,  Object>> getOrderedUserIdList();


    // fetch same data in different way

    // fetch all the userId list who have order
        @Query(value = "select a.user_id from user as a\n" +
            "inner join orders as b on a.user_id = b.user_id\n" +
            "inner join ordered_book as c on b.order_id = c.order_id group by a.user_id", nativeQuery = true)
    List<String> getOrderedUserIdList();


        // fetch user details by userId
    @Query(value = "select  new com.bookStore.bookStoreManagement.dto.UserDetailDto(a.userId, a.fullName, a.contact,sum(b.totalAmount) as totalAmount) " +
            "from User as a\n" +
            "inner join Orders as b on a.userId = b.userId\n" +
            "inner join OrderedBook as c on b.orderId = c.orderId where a.userId =?1 group by a.userId")
    UserDetailDto getOrderedUserDetailsByUserId(String userId);

    // fetch the ordered book details
    @Query(value = "select new com.bookStore.bookStoreManagement.dto.OrderDetailsDto(a.orderId, b.bookId, \n" +
            "b.bookPrice)\n" +
            "FROM Orders AS a\n" +
            "INNER JOIN OrderedBook AS b ON a.orderId = b.orderId \n" +
            "WHERE a.userId =?1")
    List<OrderDetailsDto> getOrderDetailsByUserId(String userId);


    // fetch book information by book id

    @Query("select new com.bookStore.bookStoreManagement.dto.BookInfoDto(title, description, author) from Book where bookId =?1")
    BookInfoDto getBookInfoByBookId(String bookId);

    // update the quantity of the book after ordered

    @Modifying
    @Query(value = "UPDATE book SET quantity = quantity - ?1 WHERE book_id = ?2", nativeQuery = true)
    int updateBookQuentityAfterOrderdPlaced(double quantity, String bookId);




}

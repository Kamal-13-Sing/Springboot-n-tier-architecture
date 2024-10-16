package com.bookStore.bookStoreManagement.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDetailsDto {

    private String orderId;
    private String userId;
    private String bookId;
    private double bookPrice;
    private double totalAmount;


}

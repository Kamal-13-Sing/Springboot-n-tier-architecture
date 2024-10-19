package com.bookStore.bookStoreManagement.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDetailsDto {

    private String orderId;
    private String bookId;
    private double totalAmount;

}

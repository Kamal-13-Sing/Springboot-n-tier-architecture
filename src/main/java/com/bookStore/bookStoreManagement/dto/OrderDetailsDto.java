package com.bookStore.bookStoreManagement.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDetailsDto {

    private String orderId;
    private String bookId;
    private double bookPrice;

    private Object bookDetails;

    public OrderDetailsDto(String orderId, String bookId, double bookPrice) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.bookPrice = bookPrice;
    }

    public Object getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(Object bookDetails) {
        this.bookDetails = bookDetails;
    }
}

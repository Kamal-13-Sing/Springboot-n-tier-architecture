package com.bookStore.bookStoreManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDetailDto {

    private String userId;
    private String fullName;
    private String contact;
    private double totalAmount;

    private Object order;

    public UserDetailDto(String userId, String fullName, String contact, double totalAmount) {
        this.userId = userId;
        this.fullName = fullName;
        this.contact = contact;
        this.totalAmount = totalAmount;
    }

    public Object getOrder() {
        return order;
    }

    public void setOrder(Object order) {
        this.order = order;
    }

}

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

}

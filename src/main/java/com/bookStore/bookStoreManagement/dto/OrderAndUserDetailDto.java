package com.bookStore.bookStoreManagement.dto;


import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderAndUserDetailDto {

    private  List<UserDetailDto> userDetailDtoList;
    private List<OrderDetailsDto> orderDetailsDto;

}

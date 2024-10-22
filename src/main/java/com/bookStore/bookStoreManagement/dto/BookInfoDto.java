package com.bookStore.bookStoreManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookInfoDto {

    private String title;

    private String description;

    private String author;



}

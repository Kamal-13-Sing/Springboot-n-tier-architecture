package com.bookStore.bookStoreManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookDto {

    private Integer id;

    private String title;

    private String author;

    private double price;

    private String description;

}

package com.bookStore.bookStoreManagement.helper;

import com.bookStore.bookStoreManagement.dto.BookDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookDtoHelper {

    // Book Json to Book Dto Object

    public static BookDto convertBookDtoToBookObject(String BookJson) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(BookJson, BookDto.class);
    }
}

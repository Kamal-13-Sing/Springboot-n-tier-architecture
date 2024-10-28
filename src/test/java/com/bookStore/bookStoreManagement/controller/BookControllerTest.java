package com.bookStore.bookStoreManagement.controller;


import com.bookStore.bookStoreManagement.model.Book;
import com.bookStore.bookStoreManagement.service.BookService;
import com.bookStore.bookStoreManagement.util.BookConstants;
import com.bookStore.bookStoreManagement.util.Response;
import com.bookStore.bookStoreManagement.util.ValidationConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.print.attribute.standard.Media;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@ExtendWith(MockitoExtension.class)
public class BookControllerTest  implements BookConstants, ValidationConstants {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Autowired
    private BookController bookController;


    @Test
    public void testAddNewBook() throws Exception {

        String jsonData = "{\n" +
                "  \"bookId\": \"MGM111\",\n" +
                "  \"title\": \"Fast and Furesh\",\n" +
                "  \"author\": \"the Rock\",\n" +
                "  \"price\": \"1700\",\n" +
                "  \"description\": \"this is the book of Fast and Furesh\"\n" +
                "}";

        Response expectedResonse = new Response(true, BOOK_SAVED_SUCCESSFULLY, null);

        when(bookService.addBook(jsonData)).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(post("/api/book/add-book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData)).andExpect(status().isOk()).andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        Response actualResponce = objectMapper.readValue(content, Response.class);

        assertThat(actualResponce.getStatus()).isEqualTo(true);

                // Verify that the saveUserRole method was called
        verify(bookService).addBook(jsonData);


    }


    // test case for fetch all book

    @Test
    public void testfetchAllBook(){

        List<Book> mockBooks = new ArrayList<>();

        // if book is availiability
        mockBooks.add(new Book(1,"BG101","Title","kamal",200d, "test book", 15d));
        mockBooks.add(new Book(2,"BG101","Title","kamal",200d, "test book", 15d));

        when(bookService.getAllBooks()).thenReturn(mockBooks);

        Response response = bookController.getAllBookList();

        assertThat(response.getStatus()).isEqualTo(true);
        assertThat(response.getMessage()).isEqualTo(BOOK_FOUND_SUCCESSFULLY);
        assertThat(response.getObject()).isEqualTo(mockBooks);

        // if not
//        assertThat(response.getStatus()).isEqualTo(false);
//        assertThat(response.getMessage()).isEqualTo(BOOK_NOT_FOUND);
//        assertThat(response.getObject()).isEqualTo(null);

// Verify interaction with the mock
        verify(bookService).getAllBooks();

    }


    // test for book delete



}

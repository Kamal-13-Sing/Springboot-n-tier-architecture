package com.bookStore.bookStoreManagement.controller;


import com.bookStore.bookStoreManagement.dto.BookDto;
import com.bookStore.bookStoreManagement.helper.BookHelper;
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
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

    @MockBean
    private BookHelper bookHelper;

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


    // test Get by book id

    @Test
    public void testGetBookByBookId(){

        Book mockBook = new Book(100,"BG101","Title","kamal",200d, "test book", 15d);

        when(bookService.getBookByBookId(100)).thenReturn(mockBook);

        Response actualResponse = bookController.getBookByBookId(100);

        System.out.println("actualResponse nol: "+actualResponse);
        assertThat(actualResponse.getObject()).isEqualTo(mockBook);

        verify(bookService).getBookByBookId(100); // Verify interaction with mock


    }


    // update book

    @Test
    public void testUpdateBook() throws  Exception{

        // 1.  Arrange
        String jsonData = "{\n" +
                "    \"id\": \"100\",\n" +
                "  \"bookId\": \"GAT12\",\n" +
                "  \"title\": \"Fast Title update\",\n" +
                "  \"author\": \"the Rock\",\n" +
                "  \"price\": \"1700\",\n" +
                "  \"description\": \"test book\",\n" +
                "  \"quantity\": \"15\"\n" +
                "}";


        // this is json data that is mapped into bookDto object
        BookDto mockBookDto = new BookDto(100,"GAT12","Fast Title update","the Rock",1700d, "test book", 15d);

        // this is existing data in the database
        Book mockBook = new Book(100,"GAT12","Fast Title","the Rock",1700d, "test book", 15d);



        when(bookHelper.validateUpdateBook(jsonData)).thenReturn(Collections.emptyList());
        //when(bookHelper.convertBookDtoToBookObject(jsonData)).thenReturn(mockBookDto);
        when(bookService.getBookByBookId(mockBookDto.getId())).thenReturn(mockBook);
        when(bookService.updateBook(jsonData)).thenReturn(BOOK_UPDATED_SUCCESSFULLY);

//        // 2. Act
//        Response actualResponse = bookController.updateBook(jsonData);
//
//        System.out.println("ActualResponse: "+actualResponse);
//        // 3. Assert
//        assertThat(actualResponse.getMessage()).isEqualTo(BOOK_UPDATED_SUCCESSFULLY);

        MvcResult mvcResult = mockMvc.perform(put("/api/book/update-book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Response actualResponse = objectMapper.readValue(content, Response.class);
        System.out.println("actual response: "+actualResponse);
        assertThat(actualResponse.getMessage()).isEqualTo(BOOK_UPDATED_SUCCESSFULLY);


    }


    // test delete book

    @Test
    public void testDeleteBook(){

        // arrange
        Integer bookId = 1;
        when(bookService.deleteBookById(bookId)).thenReturn("");


        // act

        Response actualResponse = bookController.deleteBook(String.valueOf(bookId));

        System.out.println("actualResponce: "+actualResponse);
        assertThat(actualResponse.getStatus()).isEqualTo(true);

    }


}

package com.bookStore.bookStoreManagement.service;

import com.fasterxml.jackson.core.JsonProcessingException;


public interface AdminService {

    public Boolean addBook(String jshonData) throws JsonProcessingException;
}

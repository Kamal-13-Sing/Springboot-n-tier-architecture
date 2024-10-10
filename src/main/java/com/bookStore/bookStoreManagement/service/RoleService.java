package com.bookStore.bookStoreManagement.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface RoleService {

    public boolean createRole(String jsonData) throws JsonProcessingException;
}

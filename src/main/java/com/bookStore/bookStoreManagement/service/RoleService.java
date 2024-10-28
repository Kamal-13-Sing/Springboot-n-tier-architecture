package com.bookStore.bookStoreManagement.service;

import com.bookStore.bookStoreManagement.model.UserRole;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RoleService {

    public boolean createRole(String jsonData) throws JsonProcessingException;

    public boolean saveUserRole(String jsonData) throws JsonProcessingException;
}

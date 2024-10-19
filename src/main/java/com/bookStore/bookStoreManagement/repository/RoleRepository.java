package com.bookStore.bookStoreManagement.repository;

import com.bookStore.bookStoreManagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}

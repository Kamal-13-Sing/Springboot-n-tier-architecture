package com.bookStore.bookStoreManagement.repository;

import com.bookStore.bookStoreManagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}

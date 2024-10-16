package com.bookStore.bookStoreManagement.repository;

import com.bookStore.bookStoreManagement.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}

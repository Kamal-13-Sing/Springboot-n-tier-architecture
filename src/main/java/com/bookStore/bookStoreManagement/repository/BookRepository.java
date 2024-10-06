package com.bookStore.bookStoreManagement.repository;

import com.bookStore.bookStoreManagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}

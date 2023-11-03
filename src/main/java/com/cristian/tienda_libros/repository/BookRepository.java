package com.cristian.tienda_libros.repository;

import com.cristian.tienda_libros.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}

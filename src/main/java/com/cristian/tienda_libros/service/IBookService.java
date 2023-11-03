package com.cristian.tienda_libros.service;

import com.cristian.tienda_libros.model.Book;

import java.util.List;

public interface IBookService {

    public List<Book> showAllbooks();

    public Book searchById(Integer idStudent);

    public void saveBook(Book book);

    public void deleteBook(Book book);
}

package com.cristian.tienda_libros.service;

import com.cristian.tienda_libros.model.Book;
import com.cristian.tienda_libros.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> showAllbooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book searchById(Integer idStudent) {
        Book book = bookRepository.findById(idStudent).orElse(null);
        if (book != null) {
            System.out.println("Libro encontrado: " + book);
            return book;
        } else
            System.out.println("el Libro no fue encontrado!");
        return book;
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}

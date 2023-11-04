package com.cristian.tienda_libros.view;

import com.cristian.tienda_libros.model.Book;
import com.cristian.tienda_libros.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Component
public class BookForm extends JFrame {
    BookService bookService;
    private JPanel panel;
    private JTable bookTable;
    private JTextField bookText;
    private JTextField authorText;
    private JTextField priceText;
    private JTextField stockText;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private DefaultTableModel tableModelBooks;

    @Autowired
    public BookForm(BookService bookService) {
        this.bookService = bookService;

        startForm();
        agregarButton.addActionListener(e -> {

        });
        modificarButton.addActionListener(e -> {

        });
        eliminarButton.addActionListener(e -> {

        });
    }

    public void startForm() {
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600, 400);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension sizeScreen = toolkit.getScreenSize();
        int x = (sizeScreen.width - getWidth() / 2);
        int y = (sizeScreen.height - getHeight() / 2);
        setLocation(x, y);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.tableModelBooks = new DefaultTableModel(0, 5); //Le indico que va a tener 5 Columnas
        String[] headers = {"ID", "Titulo", "Autor", "Precio", "Stock"};
        this.tableModelBooks.setColumnIdentifiers(headers);
        //Instancio el objeto JTable
        this.bookTable = new JTable(tableModelBooks);
        showAllBooks();
    }

    private void showAllBooks() {
        //Primero Limpio la tabla, cualquier informacion que haya quedado
        tableModelBooks.setRowCount(0);
        //Obtener todos los libros de la base de Datos
        List<Book> books = bookService.showAllbooks();
        books.forEach((book) -> {
            Object[] bookLine = {
                    book.getIdBook(),
                    book.getBookTitle(),
                    book.getAuthor(),
                    book.getPrice(),
                    book.getStock()
            };
            //Ahora agrego este objeto a la tabla de libros
            this.tableModelBooks.addRow(bookLine);
        });
    }
}

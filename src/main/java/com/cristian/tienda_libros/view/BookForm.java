package com.cristian.tienda_libros.view;

import com.cristian.tienda_libros.model.Book;
import com.cristian.tienda_libros.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@Component
public class BookForm extends JFrame {
    BookService bookService;
    private JPanel panel;
    private JTable bookTable;
    private JTextField idText;
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
        agregarButton.addActionListener(e -> bookAdd());


        modificarButton.addActionListener(e -> {

        });

        eliminarButton.addActionListener(e -> {

        });

        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loadSelectedBook();
            }
        });

        modificarButton.addActionListener(e -> modifyBook());

        eliminarButton.addActionListener(e -> deleteBook());
    }

    public void bookAdd() {
        //Voy a leer los valores del formulario
        int line = bookTable.getSelectedRow();
        String bookName = bookText.getText();
        if (bookText.getText().equals("")) {
            showMessage("Ingresa el Nombre Del Libro");
            bookText.requestFocusInWindow();
            return;
        } else if (bookText.getText().equals(bookName)){
            showMessage("El Libro ya se encuentra añadido a la Biblioteca");
            return;
        } else {
            String NewBookName = bookText.getText();
            String author = authorText.getText();
            double price = Double.parseDouble(priceText.getText());
            Integer stock = Integer.parseInt(stockText.getText());
            //Creo el objeto Libro

            Book book = new Book();
            book.setBookTitle(NewBookName);
            book.setAuthor(author);
            book.setPrice(price);
            book.setStock(stock);

            this.bookService.saveBook(book);
            showMessage("Libro Agregado!");
            clearForm();
            showAllBooks();
        }
        clearForm();
        showAllBooks();
    }

    private void loadSelectedBook() {
        //Con este metodo relleno los campos del formulario, donde esta cada libro
        //Los Indices de la tabla inician en 0.
        var line = bookTable.getSelectedRow();
        if (line != -1) { //Esto hago para validar que haya sido seleccionado un renglon valido de la tabla.
            String idBook = bookTable.getModel().getValueAt(line, 0).toString();
            //Para no consultar a la base de datos, simplemente uso la info que tengo en la tablar
            idText.setText(idBook);
            String bookName = bookTable.getModel().getValueAt(line, 1).toString();
            bookText.setText(bookName);
            String author = bookTable.getModel().getValueAt(line, 2).toString();
            authorText.setText(author);
            String price = bookTable.getModel().getValueAt(line, 3).toString();
            priceText.setText(price);
            String stock = bookTable.getModel().getValueAt(line, 4).toString();
            stockText.setText(stock);
        }
    }

    private void modifyBook() {
        if (this.idText.getText().equals("")) {
            showMessage("Debe seleccionar algun Libro para Editar");
        } else {
            //Verifico que el nombre del Titulo no sea nulo.
            if (bookText.getText().equals("")) {
                showMessage("Indica el nombre del Libro");
                bookText.requestFocusInWindow();
                return;
            }
            //Lleno el objeto del Libro a Actualizar
            int idBook = Integer.parseInt(idText.getText());
            String bookName = bookText.getText();
            String author = authorText.getText();
            double price = Double.parseDouble(priceText.getText());
            int stock = Integer.parseInt(stockText.getText());
            Book book = new Book(idBook, bookName, author, price, stock);
            bookService.saveBook(book);
            showMessage("Libro Modificado Exitosamente!");
            clearForm();
            showAllBooks();
        }
        //</editor-fold>
    }

    private void deleteBook() {
        int line = bookTable.getSelectedRow();
        if (line != -1) {
            String idBook = bookTable.getModel().getValueAt(line, 0).toString();
            Book book = new Book();
            book.setIdBook(Integer.parseInt(idBook));
            bookService.deleteBook(book);
            showMessage("Libro Eliminado Exitosamente! ");
            clearForm();
            showAllBooks();
        } else showMessage("No se selecciono ningun Libro");
    }


    private void clearForm() {
        bookText.setText("");
        authorText.setText("");
        priceText.setText("");
        stockText.setText("");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void startForm() {
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900, 600);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension sizeScreen = toolkit.getScreenSize();
        int x = (sizeScreen.width - getWidth()) / 2;
        int y = (sizeScreen.height - getHeight()) / 2;
        setLocation(x, y);
    }

    private void createUIComponents() {
        // Creamos el elemento idText oculto.
        idText = new JTextField("");
        idText.setVisible(false);
        //Le indico que va a tener 5 Columnas
        this.tableModelBooks = new DefaultTableModel(0, 5){
            //No permito que la tabla sea editable
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        String[] headers = {"ID", "Titulo", "Autor", "Precio", "Stock"};
        this.tableModelBooks.setColumnIdentifiers(headers);
        //Instancio el objeto JTable
        this.bookTable = new JTable(tableModelBooks);
        //Evitar que el usuario seleccione varios campos en la tabla, deshabilito lo siguiente
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

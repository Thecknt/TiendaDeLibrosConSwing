package com.cristian.tienda_libros.view;

import com.cristian.tienda_libros.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class BookForm extends JFrame {
    BookService bookService;
    private JPanel panel;

    @Autowired
    public BookForm(BookService bookService) {
        this.bookService = bookService;

        startForm();
    }

    public void startForm(){
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1024,768);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension sizeScreen = toolkit.getScreenSize();
        int x = (sizeScreen.width - getWidth() / 2);
        int y = (sizeScreen.height - getHeight() /2);
        setLocation(x,y);
    }
}

package com.cristian.tienda_libros.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idBook;
    String bookTitle;
    String author;
    double price;
    Integer stock;


    @Override
    public String toString() {
        return  "*************************"+
                "\n* Libro N°: " + idBook +
                "\n* Titulo: " + bookTitle +
                "\n* Autor: " + author +
                "\n* Precio: $" + price +
                "\n* Cantidad: " + stock +
                "\n************************"
                ;
    }
}

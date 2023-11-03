package com.cristian.tienda_libros;

import com.cristian.tienda_libros.view.BookForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

@SpringBootApplication
public class TiendaLibrosApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext springContext =
				new SpringApplicationBuilder(TiendaLibrosApplication.class)
						.headless(false)
						.web(WebApplicationType.NONE)
						.run(args);

		// Ejecuto el codigo para cargar el formulario

		EventQueue.invokeLater(()->{
			//Obtengo el objeto form a traves de Spring
			BookForm bookForm = springContext.getBean(BookForm.class);
			bookForm.setVisible(true);
		});
	}


}

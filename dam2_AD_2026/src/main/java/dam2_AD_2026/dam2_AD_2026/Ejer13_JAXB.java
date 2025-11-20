package dam2_AD_2026.dam2_AD_2026;

import jakarta.xml.bind.JAXBContext;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class Ejer13_JAXB {
	
	public static void main(String[] args) {
		final String RUTA = "./src/main/java/dam2_AD_2026/dam2_AD_2026/biblioteca.xml/";
		try {
			BibliotecaEjer13 biblioteca = new BibliotecaEjer13();
			
			LibroEjer13 l1 = new LibroEjer13("1111", "Java para DAM", "Genaro", 2000, 69.90);
			LibroEjer13 l2 = new LibroEjer13("2222", "c++", "Juan pEña", 2001, 29.90);
			LibroEjer13 l3 = new LibroEjer13("3333", "python", "Jose Luis", 2002, 79.90);
			LibroEjer13 l4 = new LibroEjer13("4444", "JavaScript", "Antonio Campaba", 2003, 39.90);
			
			biblioteca.agregarLibro(l1);
			biblioteca.agregarLibro(l2);
			biblioteca.agregarLibro(l3);
			biblioteca.agregarLibro(l4);
			
			JAXBContext Context = JAXBContext.newInstance(BibliotecaEjer13.class);
			Marshaller marshaller = Context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			
			File archivoOriginal = new File(RUTA + "biblioteca.xml");
			
			marshaller.marshal(biblioteca, archivoOriginal);
			System.out.println("Hemos serializado biblioteca.xml.");
			
			// Deserialización
			Unmarshaller unmarshaller = Context.createUnmarshaller();	
			BibliotecaEjer13 biblioDes = (BibliotecaEjer13) unmarshaller.unmarshal(archivoOriginal);
			
			System.out.println("Hemos deserializado biblioteca.xml.");
			
			biblioDes.getLibros().forEach(System.out::println);
			
			//Buscamos un libro por ISBN
			LibroEjer13 libroBuscado = biblioDes.buscarPorISBN("3333");
			if (libroBuscado != null) {
				System.out.println("Libro encontrado por ISBN 3333: " + libroBuscado);
			} else {
				System.out.println("Libro con ISBN 3333 no encontrado.");
			}
			
			//Precio promedio
			double precioPromedio = biblioDes.calcularPrecioPromedio();
			System.out.println("Precio promedio de los libros: " + precioPromedio);
			
			//Eliminar libro por ISBN
			if (biblioDes.eliminarPorISBN("3333")) {
				System.out.println("Libro con ISBN 3333 eliminado.");
			} else {
				System.out.println("Libro con ISBN 3333 no encontrado para eliminar.");
			}
			
			//serializamos de nuevo para guardar los cambios
			File archivoNew = new File(RUTA + "biblioteca_actualizada.xml");
			marshaller.marshal(biblioDes, archivoNew);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
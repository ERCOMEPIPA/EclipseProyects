package dam2_AD_2026.dam2_AD_2026;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "biblioteca")
@XmlAccessorType(XmlAccessType.FIELD)
public class BibliotecaEjer13 {
	
	@XmlElementWrapper(name = "libros")
	@XmlElement(name = "libro")
	private List<LibroEjer13> libros;
	
	// Constructor vacío (requerido por JAXB)
	public BibliotecaEjer13() {
		this.libros = new ArrayList<>();
	}
	
	// Constructor parametrizado
	public BibliotecaEjer13(List<LibroEjer13> libros) {
		this.libros = libros;
	}
	
	// Getters y Setters
	public List<LibroEjer13> getLibros() {
		return libros;
	}
	
	public void setLibros(List<LibroEjer13> libros) {
		this.libros = libros;
	}
	
	/**
	 * Agrega un libro a la biblioteca
	 */
	public void agregarLibro(LibroEjer13 libro) {
		libros.add(libro);
	}
	
	/**
	 * Busca un libro por ISBN
	 */
	public LibroEjer13 buscarPorISBN(String isbn) {
		return libros.stream()
				.filter(l -> l.getIsbn().equalsIgnoreCase(isbn))
				.findFirst()
				.orElse(null);
	}
	
	/**
	 * Busca libros por autor
	 */
	public List<LibroEjer13> buscarPorAutor(String autor) {
		return libros.stream()
				.filter(l -> l.getAutor().equalsIgnoreCase(autor))
				.collect(Collectors.toList());
	}
	
	/**
	 * Busca libros por año de publicación
	 */
	public List<LibroEjer13> buscarPorAnio(int anio) {
		return libros.stream()
				.filter(l -> l.getAnioPublicacion() == anio)
				.collect(Collectors.toList());
	}
	
	/**
	 * Calcula el precio promedio de los libros
	 */
	public double calcularPrecioPromedio() {
		return libros.stream()
				.mapToDouble(LibroEjer13::getPrecio)
				.average()
				.orElse(0.0);
	}
	
	/**
	 * Obtiene el libro más caro
	 */
	public LibroEjer13 obtenerLibroMasCaro() {
		return libros.stream()
				.max((l1, l2) -> Double.compare(l1.getPrecio(), l2.getPrecio()))
				.orElse(null);
	}
	
	/**
	 * Obtiene el libro más barato
	 */
	public LibroEjer13 obtenerLibroMasBarato() {
		return libros.stream()
				.min((l1, l2) -> Double.compare(l1.getPrecio(), l2.getPrecio()))
				.orElse(null);
	}
	
	/**
	 * Calcula el costo total de la biblioteca
	 */
	public double calcularCostoTotal() {
		return libros.stream()
				.mapToDouble(LibroEjer13::getPrecio)
				.sum();
	}
	
	/**
	 * Obtiene todos los títulos de los libros
	 */
	public List<String> obtenerTitulos() {
		return libros.stream()
				.map(LibroEjer13::getTitulo)
				.collect(Collectors.toList());
	}
	
	/**
	 * Obtiene todos los autores únicos
	 */
	public List<String> obtenerAutoresUnicos() {
		return libros.stream()
				.map(LibroEjer13::getAutor)
				.distinct()
				.collect(Collectors.toList());
	}
	
	/**
	 * Filtra libros por rango de precio
	 */
	public List<LibroEjer13> filtrarPorRangoPrecios(double precioMin, double precioMax) {
		return libros.stream()
				.filter(l -> l.getPrecio() >= precioMin && l.getPrecio() <= precioMax)
				.collect(Collectors.toList());
	}
	
	/**
	 * Ordena los libros por precio (ascendente)
	 */
	public List<LibroEjer13> ordenarPorPrecio() {
		return libros.stream()
				.sorted((l1, l2) -> Double.compare(l1.getPrecio(), l2.getPrecio()))
				.collect(Collectors.toList());
	}
	
	/**
	 * Ordena los libros por titulo (alfabéticamente)
	 */
	public List<LibroEjer13> ordenarPorTitulo() {
		return libros.stream()
				.sorted((l1, l2) -> l1.getTitulo().compareTo(l2.getTitulo()))
				.collect(Collectors.toList());
	}
	
	/**
	 * Ordena los libros por año de publicación (descendente)
	 */
	public List<LibroEjer13> ordenarPorAnioDescendente() {
		return libros.stream()
				.sorted((l1, l2) -> Integer.compare(l2.getAnioPublicacion(), l1.getAnioPublicacion()))
				.collect(Collectors.toList());
	}
	
	/**
	 * Cuenta los libros
	 */
	public long contarLibros() {
		return libros.stream()
				.count();
	}
	
	/**
	 * Verifica si existe un libro con un título específico
	 */
	public boolean existeLibroConTitulo(String titulo) {
		return libros.stream()
				.anyMatch(l -> l.getTitulo().equalsIgnoreCase(titulo));
	}
	
	/**
	 * Elimina un libro por ISBN
	 */
	public boolean eliminarPorISBN(String isbn) {
		return libros.removeIf(l -> l.getIsbn().equalsIgnoreCase(isbn));
	}
	
	/**
	 * Obtiene libros publicados después de un año específico
	 */
	public List<LibroEjer13> librosPublicadosDespuesDe(int anio) {
		return libros.stream()
				.filter(l -> l.getAnioPublicacion() > anio)
				.collect(Collectors.toList());
	}
	
	/**
	 * Obtiene un resumen de la biblioteca
	 */
	@Override
	public String toString() {
		return "BibliotecaEjer13 [" +
				"Total de libros: " + contarLibros() +
				", Costo total: $" + String.format("%.2f", calcularCostoTotal()) +
				", Precio promedio: $" + String.format("%.2f", calcularPrecioPromedio()) +
				"]";
	}
}

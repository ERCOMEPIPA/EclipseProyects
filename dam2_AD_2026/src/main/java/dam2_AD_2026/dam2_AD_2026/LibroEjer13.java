package dam2_AD_2026.dam2_AD_2026;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "libro")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibroEjer13 {
	
	@XmlAttribute
	private String isbn;
	
	@XmlElement
	private String titulo;
	
	@XmlElement
	private String autor;
	
	@XmlElement
	private int anioPublicacion;
	
	@XmlElement
	private double precio;
	
	// Constructor vacío (requerido por JAXB)
	public LibroEjer13() {
	}
	
	// Constructor parametrizado
	public LibroEjer13(String isbn, String titulo, String autor, int anioPublicacion, double precio) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.anioPublicacion = anioPublicacion;
		this.precio = precio;
	}
	
	// Getters
	public String getIsbn() {
		return isbn;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public int getAnioPublicacion() {
		return anioPublicacion;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	// Setters
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public void setAnioPublicacion(int anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor 
			+ ", anioPublicacion=" + anioPublicacion + ", precio=" + precio + "€]";
	}
}

package dam2_AD_2026.dam2_AD_2026;

import jakarta.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
public class EmpleadoEjer11 {
	
	@XmlAttribute
	private int id;
	
	@XmlElement
	private String nombre;
	
	@XmlElement
	private String apellido;
	
	@XmlElement
	private double salario;
	
	// Constructor vacío (requerido por JAXB)
	public EmpleadoEjer11() {
	}
	
	// Constructor parametrizado
	public EmpleadoEjer11(int id, String nombre, String apellido, double salario) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.salario = salario;
	}
	
	// Getters
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public double getSalario() {
		return salario;
	}
	
	// Setters
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void setSalario(double salario) {
		this.salario = salario;
	}
}

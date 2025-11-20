package dam2_AD_2026.dam2_AD_2026;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "empresa")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmpresaEjer11 {
	
	@XmlAttribute
	private String nombre;
	
	@XmlElementWrapper(name = "empleados")
	@XmlElement(name = "empleado")
	private List<EmpleadoEjer11> empleados;
	
	// Constructor vacío (requerido por JAXB)
	public EmpresaEjer11() {
	}
	
	// Constructor parametrizado
	public EmpresaEjer11(String nombre, List<EmpleadoEjer11> empleados) {
		this.nombre = nombre;
		this.empleados = empleados;
	}
	
	// Getters
	public String getNombre() {
		return nombre;
	}
	
	public List<EmpleadoEjer11> getEmpleados() {
		return empleados;
	}
	
	// Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setEmpleados(List<EmpleadoEjer11> empleados) {
		this.empleados = empleados;
	}
}

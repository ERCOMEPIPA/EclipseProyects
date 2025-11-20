package dam2_AD_2026.dam2_AD_2026;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "empresa")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "empleados" }) // orden de elementos dentro de <empresa>
public class Empresa {

    @XmlAttribute(name = "nombre")
    private String nombre;

    @XmlElementWrapper(name = "empleados")
    @XmlElement(name = "empleado")
    private List<Empleado> empleados = new ArrayList<>(); // evita NPE

    // Constructor vacio - Requerido por JAXB
    public Empresa() {}

    public Empresa(String nombre, List<Empleado> empleados) {
        this.nombre = nombre;
        if (empleados != null) 
        	this.empleados.addAll(empleados);
    }

    // Getters/setters opcionales
    public String getNombre() { 
    	return nombre; 
    }
    public void setNombre(String nombre) { 
    	this.nombre = nombre; 
    }

    public List<Empleado> getEmpleados() { 
    	return empleados; 
    }
    public void setEmpleados(List<Empleado> empleados) {
        this.empleados.clear();
        if (empleados != null) 
        	this.empleados.addAll(empleados);
    }
}

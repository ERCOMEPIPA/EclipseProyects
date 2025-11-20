package dam2_AD_2026.dam2_AD_2026;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "empleado", propOrder = { "nombre", "apellido", "salario" })
public class Empleado {

    @XmlAttribute(name = "id")
    private int id;

    @XmlElement(name = "nombre")
    private String nombre;

    @XmlElement(name = "apellido")
    private String apellido;

    @XmlElement(name = "salario")
    private double salario;

    // Requerido por JAXB
    public Empleado() {}

    public Empleado(int id, String nombre, String apellido, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
    }

    // Getters/setters opcionales (con FIELD no son necesarios para JAXB)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    
    @Override
    public String toString() {
        return nombre + " \t" + apellido + " \t" + salario + " €";
    }
}

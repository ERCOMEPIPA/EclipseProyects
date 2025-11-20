package dam2_AD_2026.dam2_AD_2026;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "producto")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"nombre", "categoria", "precio", "stock"})
public class Producto {

    @XmlAttribute(name = "codigo")
    private String codigo;

    @XmlElement(name = "nombre")
    private String nombre;

    @XmlElement(name = "categoria")
    private String categoria;

    @XmlElement(name = "precio")
    private Double precio;

    @XmlElement(name = "stock")
    private int stock;

    // Constructor vacío requerido por JAXB
    public Producto() {
    }

    // Constructor con parámetros
    public Producto(String codigo, String nombre, String categoria, Double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", valorTotal=" + (precio * stock) +
                '}';
    }
}

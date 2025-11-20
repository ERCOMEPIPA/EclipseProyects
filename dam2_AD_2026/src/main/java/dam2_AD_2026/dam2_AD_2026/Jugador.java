package dam2_AD_2026.dam2_AD_2026;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "jugador")
@XmlAccessorType(XmlAccessType.FIELD)
public class Jugador {

    @XmlAttribute
    private int id;
    
    @XmlElement
    private String nombre;
    
    @XmlElement
    private String posicion;
    
    @XmlElement
    private int anioFichaje;
    
    @XmlElement
    private double precioCompra;
    
    @XmlElement
    private String clubActual;

    // Constructor por defecto (requerido por JAXB)
    public Jugador() {
    }

    // Constructor con parámetros
    public Jugador(int id, String nombre, String posicion, int anioFichaje, double precioCompra, String clubActual) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
        this.anioFichaje = anioFichaje;
        this.precioCompra = precioCompra;
        this.clubActual = clubActual;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getAnioFichaje() {
        return anioFichaje;
    }

    public void setAnioFichaje(int anioFichaje) {
        this.anioFichaje = anioFichaje;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getClubActual() {
        return clubActual;
    }

    public void setClubActual(String clubActual) {
        this.clubActual = clubActual;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", posicion='" + posicion + '\'' +
                ", anioFichaje=" + anioFichaje +
                ", precioCompra=$" + String.format("%.2f", precioCompra) +
                ", clubActual='" + clubActual + '\'' +
                '}';
    }
}

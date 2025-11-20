package dam2_AD_2026.dam2_AD_2026;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import java.util.*;
import java.util.stream.Collectors;

@XmlRootElement(name = "equipo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Equipo {

    @XmlElement(name = "jugador")
    private List<Jugador> jugadores;

    // Constructor por defecto (requerido por JAXB)
    public Equipo() {
        this.jugadores = new ArrayList<>();
    }

    // Constructor con parámetros
    public Equipo(List<Jugador> jugadores) {
        this.jugadores = new ArrayList<>(jugadores);
    }

    // Getters y Setters
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    // Método: Añadir un jugador al equipo
    public void agregarJugador(Jugador jugador) {
        if (jugador != null && !existeJugador(jugador.getId())) {
            jugadores.add(jugador);
            System.out.println("✓ Jugador agregado: " + jugador.getNombre());
        } else if (existeJugador(jugador.getId())) {
            System.out.println("✗ Error: Ya existe un jugador con el ID " + jugador.getId());
        }
    }

    // Método auxiliar: Verificar si un jugador existe por ID
    private boolean existeJugador(int id) {
        return jugadores.stream().anyMatch(j -> j.getId() == id);
    }

    // Método: Buscar un jugador por ID
    public Jugador buscarPorID(int id) {
        return jugadores.stream()
                .filter(j -> j.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Método: Calcular el valor promedio de compra
    public double calcularValorPromedio() {
        if (jugadores.isEmpty()) {
            return 0.0;
        }
        return jugadores.stream()
                .mapToDouble(Jugador::getPrecioCompra)
                .average()
                .orElse(0.0);
    }

    // Método: Eliminar un jugador por ID
    public boolean eliminarJugadorPorID(int id) {
        boolean removed = jugadores.removeIf(j -> j.getId() == id);
        if (removed) {
            System.out.println("✓ Jugador con ID " + id + " eliminado correctamente.");
        } else {
            System.out.println("✗ No se encontró jugador con ID: " + id);
        }
        return removed;
    }

    // Método BONUS: Obtener jugadores por posición
    public List<Jugador> obtenerPorPosicion(String posicion) {
        return jugadores.stream()
                .filter(j -> j.getPosicion().equalsIgnoreCase(posicion))
                .collect(Collectors.toList());
    }

    // Método BONUS: Obtener el jugador más caro
    public Jugador obtenerJugadorMasCaro() {
        return jugadores.stream()
                .max(Comparator.comparingDouble(Jugador::getPrecioCompra))
                .orElse(null);
    }

    // Método BONUS: Obtener el jugador más barato
    public Jugador obtenerJugadorMasBarato() {
        return jugadores.stream()
                .min(Comparator.comparingDouble(Jugador::getPrecioCompra))
                .orElse(null);
    }

    // Método BONUS: Calcular valor total del equipo
    public double calcularValorTotalEquipo() {
        return jugadores.stream()
                .mapToDouble(Jugador::getPrecioCompra)
                .sum();
    }

    // Método auxiliar: Obtener cantidad de jugadores
    public int cantidadJugadores() {
        return jugadores.size();
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "cantidad de jugadores=" + jugadores.size() +
                ", valor total=$" + String.format("%.2f", calcularValorTotalEquipo()) +
                ", valor promedio=$" + String.format("%.2f", calcularValorPromedio()) +
                '}';
    }
}

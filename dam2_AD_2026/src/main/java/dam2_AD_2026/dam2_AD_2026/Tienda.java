package dam2_AD_2026.dam2_AD_2026;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlType;
import java.util.*;
import java.util.stream.Collectors;

@XmlRootElement(name = "tienda")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"productos"})
public class Tienda {

    @XmlElement(name = "producto")
    private List<Producto> productos;

    // Constructor por defecto (requerido por JAXB)
    public Tienda() {
        this.productos = new ArrayList<>();
    }

    // Constructor con parámetros
    public Tienda(List<Producto> productos) {
        this.productos = new ArrayList<>(productos);
    }

    // Getters y Setters
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    // Método: Agregar un nuevo producto al catálogo
    public void agregarProducto(Producto producto) {
        if (producto != null && !existeProducto(producto.getCodigo())) {
            productos.add(producto);
            System.out.println("✓ Producto agregado: " + producto.getNombre());
        } else if (existeProducto(producto.getCodigo())) {
            System.out.println("✗ Error: Ya existe un producto con el código " + producto.getCodigo());
        }
    }

    // Método auxiliar: Verificar si un producto existe
    private boolean existeProducto(String codigo) {
        return productos.stream().anyMatch(p -> p.getCodigo().equals(codigo));
    }

    // Método: Buscar un producto por su código
    public Producto buscarPorCodigo(String codigo) {
        return productos.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    // Método: Calcular el valor total del inventario
    public double calcularValorTotalInventario() {
        return productos.stream()
                .mapToDouble(p -> p.getPrecio() * p.getStock())
                .sum();
    }

    // Método: Eliminar un producto por su código
    public boolean eliminarPorCodigo(String codigo) {
        boolean removed = productos.removeIf(p -> p.getCodigo().equals(codigo));
        if (removed) {
            System.out.println("✓ Producto eliminado: " + codigo);
        } else {
            System.out.println("✗ No se encontró producto con código: " + codigo);
        }
        return removed;
    }

    // Método: Ordenar los productos por precio (menor a mayor)
    public List<Producto> ordenarPorPrecio() {
        return productos.stream()
                .sorted(Comparator.comparingDouble(Producto::getPrecio))
                .collect(Collectors.toList());
    }

    // Método BONUS: Ordenar por nombre alfabéticamente
    public List<Producto> ordenarPorNombre() {
        return productos.stream()
                .sorted(Comparator.comparing(Producto::getNombre))
                .collect(Collectors.toList());
    }

    // Método: Filtrar productos por categoría
    public List<Producto> filtrarPorCategoria(String categoria) {
        return productos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    // Método: Productos con stock bajo
    public List<Producto> productosConStockBajo(int stockMinimo) {
        return productos.stream()
                .filter(p -> p.getStock() < stockMinimo)
                .collect(Collectors.toList());
    }

    // Método BONUS: Encontrar el producto más caro
    public Producto encontrarProductoMasCaro() {
        return productos.stream()
                .max(Comparator.comparingDouble(Producto::getPrecio))
                .orElse(null);
    }

    // Método BONUS: Encontrar el producto más barato
    public Producto encontrarProductoMasBarato() {
        return productos.stream()
                .min(Comparator.comparingDouble(Producto::getPrecio))
                .orElse(null);
    }

    // Método BONUS: Reporte de productos agrupados por categoría
    public void reportePorCategoria() {
        System.out.println("\n========== REPORTE DE PRODUCTOS POR CATEGORÍA ==========");
        
        Map<String, List<Producto>> productosPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria));

        productosPorCategoria.forEach((categoria, prods) -> {
            System.out.println("\n📦 Categoría: " + categoria);
            System.out.println("───────────────────────────────────────");
            prods.forEach(p -> System.out.println("  • " + p.getNombre() + 
                    " | Precio: $" + String.format("%.2f", p.getPrecio()) + 
                    " | Stock: " + p.getStock()));
            
            double totalCategoria = prods.stream()
                    .mapToDouble(p -> p.getPrecio() * p.getStock())
                    .sum();
            System.out.println("  → Valor total categoría: $" + String.format("%.2f", totalCategoria));
        });
        
        System.out.println("\n========================================================\n");
    }

    // Método auxiliar: Obtener cantidad total de productos
    public int cantidadProductos() {
        return productos.size();
    }

    @Override
    public String toString() {
        return "Tienda{" +
                "cantidad de productos=" + productos.size() +
                ", valor total inventario=$" + String.format("%.2f", calcularValorTotalInventario()) +
                '}';
    }
}

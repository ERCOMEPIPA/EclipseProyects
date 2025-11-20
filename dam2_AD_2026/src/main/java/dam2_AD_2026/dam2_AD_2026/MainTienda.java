package dam2_AD_2026.dam2_AD_2026;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class MainTienda {

    private static final String ARCHIVO_TIENDA = "tienda.xml";
    private static final String ARCHIVO_TIENDA_ACTUALIZADA = "tienda_actualizada.xml";
    private static Tienda tienda;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        
        // Crear tienda con productos iniciales
        tienda = crearTiendaInicial();
        
        // Serializar la tienda en XML
        System.out.println("\n📁 Serializando tienda en: " + ARCHIVO_TIENDA);
        serializarTienda(tienda, ARCHIVO_TIENDA);
        
        // Deserializar la tienda desde XML
        System.out.println("📂 Deserializando tienda desde: " + ARCHIVO_TIENDA);
        tienda = deserializarTienda(ARCHIVO_TIENDA);
        
        // Menú interactivo
        mostrarMenuPrincipal();
        
        // Guardar cambios
        System.out.println("\n💾 Guardando cambios finales en: " + ARCHIVO_TIENDA_ACTUALIZADA);
        serializarTienda(tienda, ARCHIVO_TIENDA_ACTUALIZADA);
        
        System.out.println("\n✓ Programa finalizado. Archivo guardado: " + ARCHIVO_TIENDA_ACTUALIZADA);
        scanner.close();
    }

    // Crear tienda inicial con al menos 6 productos
    private static Tienda crearTiendaInicial() {
        Tienda tienda = new Tienda();
        
        tienda.agregarProducto(new Producto("P001", "Laptop Dell XPS", "ELECTRONICA", 1299.99, 5));
        tienda.agregarProducto(new Producto("P002", "Monitor LG 27\"", "ELECTRONICA", 349.99, 8));
        tienda.agregarProducto(new Producto("P003", "Camiseta Nike", "ROPA", 29.99, 25));
        tienda.agregarProducto(new Producto("P004", "Pantalón Adidas", "ROPA", 59.99, 15));
        tienda.agregarProducto(new Producto("P005", "Arroz Blanco 1kg", "ALIMENTOS", 3.99, 50));
        tienda.agregarProducto(new Producto("P006", "Lámpara LED", "HOGAR", 19.99, 7));
        tienda.agregarProducto(new Producto("P007", "Funda de Almohada", "HOGAR", 12.99, 20));
        tienda.agregarProducto(new Producto("P008", "Chocolate Belga", "ALIMENTOS", 5.49, 35));
        tienda.agregarProducto(new Producto("P009", "Auriculares Sony", "ELECTRONICA", 199.99, 3));
        tienda.agregarProducto(new Producto("P010", "Teclado Mecánico", "ELECTRONICA", 129.99, 6));
        
        return tienda;
    }

    // Menú principal interactivo
    private static void mostrarMenuPrincipal() {
        boolean salir = false;
        
        while (!salir) {
            System.out.println("\n╔════════════════════════════════════════════════════╗");
            System.out.println("║     SISTEMA DE GESTIÓN DE PRODUCTOS - TIENDA      ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.println("\n📊 Estado actual: " + tienda.toString());
            System.out.println("\n─ MENÚ PRINCIPAL ─");
            System.out.println("1. Buscar producto por código");
            System.out.println("2. Ver valor total del inventario");
            System.out.println("3. Eliminar un producto");
            System.out.println("4. Ordenar productos por precio");
            System.out.println("5. Filtrar productos por categoría");
            System.out.println("6. Ver productos con stock bajo");
            System.out.println("7. Ordenar productos por nombre");
            System.out.println("8. Encontrar producto más caro y más barato");
            System.out.println("9. Ver reporte de productos por categoría");
            System.out.println("10. Agregar nuevo producto");
            System.out.println("0. Salir y guardar");
            System.out.print("\nSeleccione una opción: ");
            
            String opcion = scanner.nextLine().trim();
            
            switch (opcion) {
                case "1":
                    buscarPorCodigo();
                    break;
                case "2":
                    verValorTotalInventario();
                    break;
                case "3":
                    eliminarProducto();
                    break;
                case "4":
                    ordenarPorPrecio();
                    break;
                case "5":
                    filtrarPorCategoria();
                    break;
                case "6":
                    verProductosConStockBajo();
                    break;
                case "7":
                    ordenarPorNombre();
                    break;
                case "8":
                    encontrarProductosExtremos();
                    break;
                case "9":
                    tienda.reportePorCategoria();
                    break;
                case "10":
                    agregarNuevoProducto();
                    break;
                case "0":
                    salir = true;
                    System.out.println("\n✓ Saliendo del programa...");
                    break;
                default:
                    System.out.println("✗ Opción no válida. Intente de nuevo.");
            }
        }
    }

    // 1. Buscar producto por código
    private static void buscarPorCodigo() {
        System.out.print("\nIngrese el código del producto: ");
        String codigo = scanner.nextLine().trim();
        Producto producto = tienda.buscarPorCodigo(codigo);
        
        if (producto != null) {
            System.out.println("\n✓ Producto encontrado:");
            System.out.println("  " + producto);
        } else {
            System.out.println("\n✗ No se encontró producto con código: " + codigo);
        }
    }

    // 2. Ver valor total del inventario
    private static void verValorTotalInventario() {
        double valorTotal = tienda.calcularValorTotalInventario();
        System.out.println("\n💰 Valor total del inventario: $" + String.format("%.2f", valorTotal));
    }

    // 3. Eliminar un producto
    private static void eliminarProducto() {
        System.out.print("\nIngrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine().trim();
        tienda.eliminarPorCodigo(codigo);
    }

    // 4. Ordenar productos por precio
    private static void ordenarPorPrecio() {
        List<Producto> ordenados = tienda.ordenarPorPrecio();
        System.out.println("\n📊 Productos ordenados por precio (menor a mayor):");
        System.out.println("────────────────────────────────────────────");
        ordenados.forEach(p -> System.out.println("  • " + p.getNombre() + 
                " | $" + String.format("%.2f", p.getPrecio()) + 
                " | Stock: " + p.getStock()));
    }

    // 5. Filtrar productos por categoría
    private static void filtrarPorCategoria() {
        System.out.println("\n📦 Categorías disponibles:");
        System.out.println("  • ELECTRONICA");
        System.out.println("  • ROPA");
        System.out.println("  • ALIMENTOS");
        System.out.println("  • HOGAR");
        System.out.print("\nIngrese la categoría: ");
        String categoria = scanner.nextLine().trim().toUpperCase();
        
        List<Producto> filtrados = tienda.filtrarPorCategoria(categoria);
        
        if (filtrados.isEmpty()) {
            System.out.println("\n✗ No se encontraron productos en la categoría: " + categoria);
        } else {
            System.out.println("\n✓ Productos en la categoría " + categoria + ":");
            System.out.println("────────────────────────────────────────────");
            filtrados.forEach(p -> System.out.println("  • " + p.getNombre() + 
                    " | Código: " + p.getCodigo() + 
                    " | $" + String.format("%.2f", p.getPrecio()) + 
                    " | Stock: " + p.getStock()));
        }
    }

    // 6. Ver productos con stock bajo
    private static void verProductosConStockBajo() {
        System.out.print("\nIngrese el límite de stock (ej: 10): ");
        try {
            int limite = Integer.parseInt(scanner.nextLine().trim());
            List<Producto> bajos = tienda.productosConStockBajo(limite);
            
            if (bajos.isEmpty()) {
                System.out.println("\n✓ No hay productos con stock menor a " + limite);
            } else {
                System.out.println("\n⚠️  Productos con stock bajo (< " + limite + "):");
                System.out.println("────────────────────────────────────────────");
                bajos.forEach(p -> System.out.println("  • " + p.getNombre() + 
                        " | Stock: " + p.getStock() + 
                        " | Código: " + p.getCodigo()));
            }
        } catch (NumberFormatException e) {
            System.out.println("\n✗ Valor no válido. Ingrese un número entero.");
        }
    }

    // 7. Ordenar por nombre
    private static void ordenarPorNombre() {
        List<Producto> ordenados = tienda.ordenarPorNombre();
        System.out.println("\n📝 Productos ordenados alfabéticamente por nombre:");
        System.out.println("────────────────────────────────────────────");
        ordenados.forEach(p -> System.out.println("  • " + p.getNombre() + 
                " | Código: " + p.getCodigo() + 
                " | $" + String.format("%.2f", p.getPrecio())));
    }

    // 8. Encontrar producto más caro y más barato
    private static void encontrarProductosExtremos() {
        Producto masCaro = tienda.encontrarProductoMasCaro();
        Producto masBarato = tienda.encontrarProductoMasBarato();
        
        System.out.println("\n💎 PRODUCTOS EXTREMOS:");
        System.out.println("────────────────────────────────────────────");
        
        if (masCaro != null) {
            System.out.println("  MÁS CARO:");
            System.out.println("    • " + masCaro.getNombre() + 
                    " | $" + String.format("%.2f", masCaro.getPrecio()) + 
                    " | Stock: " + masCaro.getStock());
        }
        
        if (masBarato != null) {
            System.out.println("  MÁS BARATO:");
            System.out.println("    • " + masBarato.getNombre() + 
                    " | $" + String.format("%.2f", masBarato.getPrecio()) + 
                    " | Stock: " + masBarato.getStock());
        }
    }

    // 10. Agregar nuevo producto
    private static void agregarNuevoProducto() {
        System.out.println("\n➕ AGREGAR NUEVO PRODUCTO");
        System.out.println("────────────────────────────────────────────");
        
        System.out.print("Código del producto: ");
        String codigo = scanner.nextLine().trim();
        
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine().trim();
        
        System.out.println("Categorías disponibles: ELECTRONICA, ROPA, ALIMENTOS, HOGAR");
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine().trim().toUpperCase();
        
        try {
            System.out.print("Precio: ");
            double precio = Double.parseDouble(scanner.nextLine().trim());
            
            System.out.print("Stock: ");
            int stock = Integer.parseInt(scanner.nextLine().trim());
            
            Producto nuevo = new Producto(codigo, nombre, categoria, precio, stock);
            tienda.agregarProducto(nuevo);
        } catch (NumberFormatException e) {
            System.out.println("✗ Error: Ingrese valores numéricos válidos.");
        }
    }

    // Serializar tienda en XML
    private static void serializarTienda(Tienda tienda, String nombreArchivo) {
        try {
            JAXBContext context = JAXBContext.newInstance(Tienda.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(tienda, new File(nombreArchivo));
            System.out.println("✓ Tienda serializada correctamente en: " + nombreArchivo);
        } catch (JAXBException e) {
            System.out.println("✗ Error al serializar la tienda: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Deserializar tienda desde XML
    private static Tienda deserializarTienda(String nombreArchivo) {
        try {
            JAXBContext context = JAXBContext.newInstance(Tienda.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File archivo = new File(nombreArchivo);
            
            if (!archivo.exists()) {
                System.out.println("✗ Archivo no encontrado: " + nombreArchivo);
                return new Tienda();
            }
            
            Tienda tiendaDeserializada = (Tienda) unmarshaller.unmarshal(archivo);
            System.out.println("✓ Tienda deserializada correctamente desde: " + nombreArchivo);
            return tiendaDeserializada;
        } catch (JAXBException e) {
            System.out.println("✗ Error al deserializar la tienda: " + e.getMessage());
            e.printStackTrace();
            return new Tienda();
        }
    }
}


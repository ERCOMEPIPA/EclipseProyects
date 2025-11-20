package dam2_AD_2026.dam2_AD_2026;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class MainJugadores {

    private static final String ARCHIVO_JUGADORES = "jugadores.xml";
    private static final String ARCHIVO_JUGADORES_ACTUALIZADO = "jugadores_actualizados.xml";

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTIÓN DE JUGADORES - EQUIPO DE FÚTBOL   ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");

        // 1. Crear un objeto Equipo y añadir jugadores
        System.out.println("\n📝 PASO 1: Creando equipo y agregando jugadores...");
        Equipo equipo = crearEquipoConJugadores();

        // 2. Serializar el equipo a un archivo XML
        System.out.println("\n💾 PASO 2: Serializando equipo en archivo XML...");
        serializarEquipo(equipo, ARCHIVO_JUGADORES);

        // 3. Deserializar el XML nuevamente al programa
        System.out.println("\n📂 PASO 3: Deserializando equipo desde archivo XML...");
        Equipo equipoDeserializado = deserializarEquipo(ARCHIVO_JUGADORES);

        // 4. Buscar un jugador por ID
        System.out.println("\n🔍 PASO 4: Buscando jugador por ID...");
        buscarJugador(equipoDeserializado, 2);

        // 5. Calcular el valor promedio de compra
        System.out.println("\n💰 PASO 5: Calculando valor promedio de compra...");
        double valorPromedio = equipoDeserializado.calcularValorPromedio();
        System.out.println("   Valor promedio de compra: $" + String.format("%.2f", valorPromedio));

        // 6. Eliminar un jugador por ID
        System.out.println("\n🗑️  PASO 6: Eliminando un jugador...");
        equipoDeserializado.eliminarJugadorPorID(3);

        // 7. Serializar nuevamente el equipo actualizado
        System.out.println("\n💾 PASO 7: Serializando equipo actualizado...");
        serializarEquipo(equipoDeserializado, ARCHIVO_JUGADORES_ACTUALIZADO);

        // Información adicional
        System.out.println("\n📊 INFORMACIÓN ADICIONAL:");
        mostrarInformacionExtra(equipoDeserializado);

        System.out.println("\n✓ Programa finalizado exitosamente.");
    }

    // Crear equipo con jugadores
    private static Equipo crearEquipoConJugadores() {
        Equipo equipo = new Equipo();

        equipo.agregarJugador(new Jugador(1, "Cristiano Ronaldo", "Delantero", 2018, 117000000, "Manchester United"));
        equipo.agregarJugador(new Jugador(2, "Lionel Messi", "Delantero", 2021, 110000000, "Inter Miami"));
        equipo.agregarJugador(new Jugador(3, "Harry Kane", "Delantero", 2020, 100000000, "Tottenham Hotspur"));
        equipo.agregarJugador(new Jugador(4, "Kylian Mbappé", "Delantero", 2022, 180000000, "Real Madrid"));
        equipo.agregarJugador(new Jugador(5, "Erling Haaland", "Delantero", 2022, 60000000, "Manchester City"));
        equipo.agregarJugador(new Jugador(6, "Vinícius Júnior", "Extremo", 2018, 60000000, "Real Madrid"));
        equipo.agregarJugador(new Jugador(7, "Jude Bellingham", "Centrocampista", 2023, 103000000, "Real Madrid"));
        equipo.agregarJugador(new Jugador(8, "Rodri", "Centrocampista", 2022, 77000000, "Manchester City"));
        equipo.agregarJugador(new Jugador(9, "Antonio Rüdiger", "Defensa", 2022, 70000000, "Real Madrid"));
        equipo.agregarJugador(new Jugador(10, "Andrew Robertson", "Defensa", 2017, 50000000, "Liverpool"));

        return equipo;
    }

    // Buscar un jugador por ID
    private static void buscarJugador(Equipo equipo, int id) {
        Jugador jugador = equipo.buscarPorID(id);
        if (jugador != null) {
            System.out.println("✓ Jugador encontrado:");
            System.out.println("   " + jugador);
        } else {
            System.out.println("✗ No se encontró jugador con ID: " + id);
        }
    }

    // Mostrar información extra
    private static void mostrarInformacionExtra(Equipo equipo) {
        System.out.println("\n   📋 Total de jugadores: " + equipo.cantidadJugadores());
        System.out.println("   💵 Valor total del equipo: $" + String.format("%.2f", equipo.calcularValorTotalEquipo()));
        System.out.println("   📊 Valor promedio: $" + String.format("%.2f", equipo.calcularValorPromedio()));

        Jugador masCaro = equipo.obtenerJugadorMasCaro();
        if (masCaro != null) {
            System.out.println("   💎 Jugador más caro: " + masCaro.getNombre() + " ($" + String.format("%.2f", masCaro.getPrecioCompra()) + ")");
        }

        Jugador masBarato = equipo.obtenerJugadorMasBarato();
        if (masBarato != null) {
            System.out.println("   🏷️  Jugador más barato: " + masBarato.getNombre() + " ($" + String.format("%.2f", masBarato.getPrecioCompra()) + ")");
        }

        System.out.println("\n   📃 Lista de jugadores actuales:");
        equipo.getJugadores().forEach(j -> System.out.println("      • " + j.getNombre() + " - " + j.getPosicion() + " (ID: " + j.getId() + ")"));
    }

    // Serializar equipo en XML
    private static void serializarEquipo(Equipo equipo, String nombreArchivo) {
        try {
            JAXBContext context = JAXBContext.newInstance(Equipo.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(equipo, new File(nombreArchivo));
            System.out.println("   ✓ Equipo serializado correctamente en: " + nombreArchivo);
        } catch (JAXBException e) {
            System.out.println("   ✗ Error al serializar el equipo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Deserializar equipo desde XML
    private static Equipo deserializarEquipo(String nombreArchivo) {
        try {
            JAXBContext context = JAXBContext.newInstance(Equipo.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File archivo = new File(nombreArchivo);

            if (!archivo.exists()) {
                System.out.println("   ✗ Archivo no encontrado: " + nombreArchivo);
                return new Equipo();
            }

            Equipo equipoDeserializado = (Equipo) unmarshaller.unmarshal(archivo);
            System.out.println("   ✓ Equipo deserializado correctamente desde: " + nombreArchivo);
            System.out.println("   📊 " + equipoDeserializado.toString());

            System.out.println("\n   📋 Jugadores deserializados:");
            equipoDeserializado.getJugadores().forEach(j -> System.out.println("      " + j));

            return equipoDeserializado;
        } catch (JAXBException e) {
            System.out.println("   ✗ Error al deserializar el equipo: " + e.getMessage());
            e.printStackTrace();
            return new Equipo();
        }
    }
}

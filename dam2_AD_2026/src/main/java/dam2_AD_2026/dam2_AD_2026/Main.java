package dam2_AD_2026.dam2_AD_2026;

import java.io.File;
//import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Main {

    public static void main(String[] args) {
    	
    	final String RUTA = "./src/main/java/Ejer12_JAXB_Completo/";
    	
        try {
            // ===== 1. DESERIALIZACIÓN =====
            JAXBContext context = JAXBContext.newInstance(Empresa.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Empresa empresa = (Empresa) unmarshaller.unmarshal(new File(RUTA + "empresa.xml"));

            
            // ===== 2. LISTAR DATOS =====
            listarEmpleados(empresa);

            // ===== 3. CONSULTAS / AGREGACIONES =====
            mostrarEstadisticas(empresa);

            // ===== 4. FILTRADO =====
            double umbral = 32000;
            List<Empleado> filtrados = empresa.getEmpleados().stream()
                    .filter(e -> e.getSalario() > umbral)
                    .collect(Collectors.toList());

            System.out.println("\nEmpleados con salario > " + umbral + ":");
            filtrados.forEach(System.out::println);

            // ===== 5. ORDENACIONES =====
            System.out.println("\nEmpleados ordenados por apellido (A-Z):");
            empresa.getEmpleados().stream()
                    .sorted(Comparator.comparing(Empleado::getApellido))
                    .forEach(System.out::println);

            System.out.println("\nEmpleados ordenados por salario (mayor a menor):");
            empresa.getEmpleados().stream()
                    .sorted(Comparator.comparingDouble(Empleado::getSalario) //Compara de menor a mayor
                            .reversed()    //invertimos el orden
                            .thenComparing(Empleado::getNombre)) //si 2 empleados tienen mismo salario ordena por orden alfabetico
                    .forEach(System.out::println); //Sobreescribimos toString() de empleados para formatearlo

            // ===== 6. PERSISTENCIA =====
            // Guardamos la empresa modificada (por si hicieras cambios)
            guardarEmpresa(context, empresa, RUTA + "empresa_actualizada.xml");

            // Guardamos también la empresa filtrada (solo los empleados > umbral)
            Empresa empresaFiltrada = new Empresa(empresa.getNombre(), filtrados);
            guardarEmpresa(context, empresaFiltrada, RUTA + "empresa_filtrada.xml");

            System.out.println("\nArchivos generados: empresa_actualizada.xml y empresa_filtrada.xml");

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // ====== MÉTODOS AUXILIARES ======

    private static void listarEmpleados(Empresa empresa) {
    	
        System.out.println("Empresa: " + empresa.getNombre());
        System.out.println("ID \tNombre Apellido \t Sueldo");
        
        for (Empleado e : empresa.getEmpleados()) {
           
        	System.out.println("[" + e.getId() + "] \t" + e.getNombre() + " " + e.getApellido() + " \t\t" + e.getSalario() + " €");
        }
        System.out.println("Total empleados: " + empresa.getEmpleados().size());
    }

    private static void mostrarEstadisticas(Empresa empresa) {
        List<Empleado> empleados = empresa.getEmpleados();
        
        //stream -> Procesar elementos de forma declarativa, como si se tratara de una cadena de operaciones.
        double sumaSalarios = empleados.stream().mapToDouble(Empleado::getSalario).sum();
        double salarioMedio = empleados.stream().mapToDouble(Empleado::getSalario).average().orElse(0);

        System.out.println("Suma total de salarios: " + sumaSalarios);
        System.out.println("Salario medio: " + salarioMedio);
 
        //Optional para prevenir que la lista empleados esté vacía, evitar null y errores
        Optional<Empleado> max = empleados.stream().max(Comparator.comparingDouble(Empleado::getSalario));
        Optional<Empleado> min = empleados.stream().min(Comparator.comparingDouble(Empleado::getSalario));
        
        //solo si Optional contiene valor imprimimos
        max.ifPresent(e -> System.out.println("Empleado con mayor salario: " + e.getNombre() + " " + e.getApellido() + " " + e.getSalario()));
        min.ifPresent(e -> System.out.println("Empleado con menor salario: " + e.getNombre() + " " + e.getApellido() + " " + e.getSalario()));
    }

    private static void guardarEmpresa(JAXBContext context, Empresa empresa, String rutaArchivo)
            throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        marshaller.marshal(empresa, new File(rutaArchivo));
    }
}
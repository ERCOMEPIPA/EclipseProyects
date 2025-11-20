package dam2_AD_2026.dam2_AD_2026;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Ejer11_JABX {
	
	public static void main(String[] args) {
		try {
			
			List<EmpleadoEjer11> empleados = new ArrayList<>();
			empleados.add(new EmpleadoEjer11(1, "Juan", "García", 2500.00));
			empleados.add(new EmpleadoEjer11(2, "María", "López", 2800.00));
			empleados.add(new EmpleadoEjer11(3, "Carlos", "Rodríguez", 3000.00));
			empleados.add(new EmpleadoEjer11(4, "Ana", "Martínez", 2600.00));
			
			// Crear instancia de Empresa
			EmpresaEjer11 empresa = new EmpresaEjer11("TechSolutions S.L.", empleados);
			
			// Configurar contexto JAXB para la clase Empresa
			JAXBContext contexto = JAXBContext.newInstance(EmpresaEjer11.class);
			
			// Crear objeto Marshaller para serializar
			Marshaller marshaller = contexto.createMarshaller();
			
			// Configurar el Marshaller para formato legible
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			
			// Ruta del archivo XML
			String rutaArchivo = "empresa.xml";
			File archivo = new File(rutaArchivo);
			
			// Escribir el objeto Empresa al archivo XML
			marshaller.marshal(empresa, archivo);
			System.out.println("✓ Archivo 'empresa.xml' generado exitosamente.\n");
			
			// Leer e imprimir el contenido del XML generado
			System.out.println("=== CONTENIDO DEL ARCHIVO XML ===\n");
			String contenidoXML = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
			System.out.println(contenidoXML);
			System.out.println("\n=== FIN DEL CONTENIDO XML ===");
			
		} catch (JAXBException e) {
			System.err.println("Error en JAXB: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

package dam2_AD_2026.dam2_AD_2026;

import jakarta.xml.bind.*;
import java.io.File;

public class MainEmpleado {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Empleado empleado = new Empleado(1, "Juan", "Pérez", 30);
			
			JAXBContext context = JAXBContext.newInstance(Empleado.class);
			
			Marshaller marshaller = context.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			
			String rutaActual = "./src/main/java/dam2_AD_2026/dam2_AD_2026/empleado.xml";
			
			marshaller.marshal(empleado, new File(rutaActual + "empleado.xml"));
			
			marshaller.marshal(empleado, System.out);
			
			
		}catch(JAXBException e) {
			e.printStackTrace();
		}
	}

}

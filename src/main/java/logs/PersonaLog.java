package logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersonaLog {

    private String nombre;

    private final static Logger logger = LogManager.getRootLogger();

    public PersonaLog(String n) {
        nombre = n;
        logger.debug("Un objeto Personalogs ha sido creado: " + nombre);
    }

    public void Comer(){
        logger.debug("Llamada a comer");
        System.out.println("Come");
    }
}

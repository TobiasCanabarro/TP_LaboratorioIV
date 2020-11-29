package edu.utn.log;

import edu.utn.enums.Result;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

//Este clase ayuda a loggear las acciones del sistema
public class LogHelper {

    private static final String NAME_CONFIG_FILE = "log4j/log.log";
    private static final String PATH_CONFIG_FILE= "C:/log4j.properties";//Es la ruta donde tiene que estar el archivo de configuracion
    private static Logger log;

    private static void initializeLog() {
        log = Logger.getLogger(NAME_CONFIG_FILE);
        PropertyConfigurator.configure(PATH_CONFIG_FILE);
    }

    public static void createNewDebugLog(Result result) {
        try {
            initializeLog();
            log.debug(result.getDescription());
        }catch (Exception exception){
            System.out.println("Hubo un problema con Log4j " + exception.getMessage());
        }
    }

    public static void createNewErrorLog(String msg){
        try {
            initializeLog();
            log.error(msg);
        }catch (Exception exception){
            System.out.println("Hubo un problema con Log4j " + exception.getMessage());
        }

    }
}

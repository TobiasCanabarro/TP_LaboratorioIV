package edu.utn.log;

import edu.utn.enums.Result;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogHelper {

    private static final String PATH_CONFIG_FILE = "E:/Documentos/GitHub/TP_LaboratorioIV/src/edu/utn/log/log4j.properties";
//    private static final String PATH_CONFIG_FILE = "../TP_LaboratorioIV/src/edu/utn/log/log4j.properties";
    private static final String NAME_CONFIG_FILE = "apiLog.log";
    private static Logger log;

    private static void initializeLog() {
        log = Logger.getLogger(NAME_CONFIG_FILE);
        PropertyConfigurator.configure(PATH_CONFIG_FILE);
    }

    public static void createNewDebugLog(Result result) {
        initializeLog();
        log.debug(result.getDescription());
    }

    public static void createNewErrorLog(String msg){
        initializeLog();
        log.error(msg);
    }
}

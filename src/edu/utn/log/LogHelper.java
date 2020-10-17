package edu.utn.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogHelper {

    //setLog(Logger.getLogger(NAME_CONFIG_FILE));
    //PropertyConfigurator.configure(PATH_CONFIG_FILE);
    private static final String PATH_CONFIG_FILE = "C:\\Users\\Toby\\Desktop\\TPProg4\\src\\edu\\utn\\log\\log4j.properties";
    private static final String NAME_CONFIG_FILE = "apiLog.log";
    private static Logger log;

    private static void initializeLog() {
        log = Logger.getLogger(NAME_CONFIG_FILE);
        PropertyConfigurator.configure(PATH_CONFIG_FILE);
    }

    public static void createNewLog(String msg) {
        initializeLog();
        log.debug(msg);
    }
}

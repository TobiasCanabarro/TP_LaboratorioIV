package edu.utn.file;

import edu.utn.log.LogHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadConfig {

    private String host;
    private String port;
    private String user;
    private String password;
    private static LoadConfig loadConfig;
    private static final String PATH_CONFIGURATION = "C:/configuration.properties";//Es la ruta de donde se obtiene la configuracion para la conexion a la base de datos

    private LoadConfig() {
        loadConfig();
    }

    public static LoadConfig getConfig () {
        if(loadConfig == null){
            loadConfig = new LoadConfig();
        }
        return loadConfig;
    }

    private void loadConfig() {
        Properties properties = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(PATH_CONFIGURATION);
            properties.load(is);
        } catch (IOException exception) {
            LogHelper.createNewErrorLog(exception.getMessage());
        }
        setHost(properties.get("db.host").toString());
        setPort(properties.get("db.port").toString());
        setUser(properties.get("db.user").toString());
        setPassword(properties.get("db.password").toString());
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static LoadConfig getLoadConfig() {
        return loadConfig;
    }

    public static void setLoadConfig(LoadConfig loadConfig) {
        LoadConfig.loadConfig = loadConfig;
    }
}

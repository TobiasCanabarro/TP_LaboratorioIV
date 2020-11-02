package edu.utn.dao;

import edu.utn.file.LoadConfig;
import edu.utn.log.LogHelper;

import java.sql.*;
import java.util.*;

public class DataAccess {

    private String connectionString;
    private String user;
    private String password;
    private String host;
    private String port;
    private Connection connection;
    private LoadConfig config;

    protected DataAccess (){
        setConfig(LoadConfig.getConfig());
        setHost(getConfig().getHost());
        setPort(getConfig().getPort());
        setUser(getConfig().getUser());
        setPassword(getConfig().getPassword());
        setConnectionString("jdbc:postgresql://" + host + ":" + port + "/cuvl_db");
    }

    protected List<Map<String, Object>> read(String query) {
        return read(query, null);
    }

    protected List<Map<String, Object>> read (String query, Map<Integer, Object> parameters) {

        List<Map<String, Object>> results = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = getStatement(query, parameters, connection);
            setMetadata(preparedStatement, results);
        } catch (SQLException ex) {
            LogHelper.createNewErrorLog(ex.getMessage());
        }  catch (Exception ex) {
            LogHelper.createNewErrorLog(ex.getMessage());
        } finally {
            return results;
        }
    }

    // En el caso de un UPDATE o DELETE, se devuelve la cantidad de registros afectados.
    // En el caso de un INSERT, se devuelve el id generado
    protected int write(String query, Map<Integer, Object> parameters){
        int returnedValue = 0;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = getStatement(query, parameters, connection);
            returnedValue = preparedStatement.executeUpdate();
            if (returnedValue > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        returnedValue = generatedKeys.getInt(1);
                    }
                } catch (Exception exception) {
                    LogHelper.createNewErrorLog("No keys to be retrieved " + exception.getMessage());
                }
            }
        } catch (SQLException ex) {
            LogHelper.createNewErrorLog(ex.getMessage());
        } catch (Exception ex) {
            LogHelper.createNewErrorLog(ex.getMessage());
        } finally {
            return returnedValue;
        }
    }

//    protected int writeTransaction (String userQuery, String userLogQuery, Map<Integer, Object> userParameters, Map<Integer, Object> userLogParameters) {
//        int returnedValue = 0;
//        int returnedValue2 = 0;
//        try {
//            Connection connection = getConnection();
//            connection.setAutoCommit(false);
//            PreparedStatement preparedStatement = getStatement(userQuery, userParameters, connection);
//            PreparedStatement preparedStatement2 = getStatement(userLogQuery, userLogParameters, connection);
//            returnedValue = preparedStatement.executeUpdate();
//            returnedValue2 = preparedStatement2.executeUpdate();
//
//            if (returnedValue > 0 && returnedValue2 > 0) {
//                connection.commit();
//                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
//                    if (generatedKeys.next()) {
//                        returnedValue = generatedKeys.getInt(1);
//                    }
//                } catch (Exception exception) {
//                    LogHelper.createNewErrorLog("No keys to be retrieved");
//                }
//            }
//        } catch (SQLException exception) {
//            if (connection != null) {
//                LogHelper.createNewErrorLog("Transaction is being rolled back");
//                connection.rollback();
//            }
//            }catch(Exception exception){
//            LogHelper.createNewErrorLog(exception.getMessage());
//            }finally {
//            return returnedValue;
//            }
//    }

    protected PreparedStatement getStatement(String query, Map<Integer, Object> parameters, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        if (parameters != null) {
            Set<Integer> keys = parameters.keySet();
            for (Integer key : keys) {
                Object value = parameters.get(key);
                preparedStatement.setObject(key, value);
            }
        }
        return preparedStatement;
    }

    protected Connection getConnection() {
        try {
            if (connection == null){
                connection = DriverManager.getConnection(getConnectionString(), getUser(), getPassword());
            }
        } catch (SQLException exception) {
            LogHelper.createNewErrorLog(exception.getMessage());
        }
        return connection;
    }

    private void setMetadata (PreparedStatement preparedStatement, List<Map<String, Object>> results) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metadata = resultSet.getMetaData();
        while(resultSet.next()) {
            HashMap<String, Object> columns = new HashMap<>();
            for (int i = 1; i <= metadata.getColumnCount(); ++i) {
                columns.put(metadata.getColumnName(i), resultSet.getObject(i));
            }
            results.add(columns);
        }
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
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

    public void setConnection(Connection connection) {
        this.connection = connection;
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

    public LoadConfig getConfig() {
        return config;
    }

    public void setConfig(LoadConfig config) {
        this.config = config;
    }
}

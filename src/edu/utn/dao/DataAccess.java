package edu.utn.dao;

import java.sql.*;
import java.util.*;

//ConnectionString = "jdbc:postgresql://192.168.33.10:5432/cuvl_db";

public class DataAccess {

    private String connectionString;
    private String user;
    private String password;
    private String host;
    private String port;
    private Connection connection;
    private static DataAccess dataAccess;

    protected DataAccess(String host, String port, String user, String password) {
        setUser(user);
        setHost(host);
        setPort(port);
        setPassword(password);
        setConnectionString("jdbc:postgresql://" + host + ":" + port + "/cuvl_db");
    }

    public static DataAccess getDataAccess(String host, String port, String user, String password){
        if(dataAccess == null) {
            dataAccess = new DataAccess(host, port, user, password);
        }
        return dataAccess;
    }

    protected List<Map<String, Object>> read(String query) {
        return read(query, null);
    }

    protected List<Map<String, Object>> read (String query, Map<Integer, Object> parameters) {

        List<Map<String, Object>> results = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = getStatement(query, parameters, connection);

            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metadata = resultSet.getMetaData();
            while(resultSet.next()) {
                HashMap<String, Object> columns = new HashMap<>();

                for (int i = 1; i <= metadata.getColumnCount(); ++i) {
                    columns.put(metadata.getColumnName(i), resultSet.getObject(i));
                }
                results.add(columns);
            }
            resultSet.close();
        } catch (SQLException exception) {
            System.out.println("SQL Exception -> " + exception.getMessage() );
        }  catch (Exception exception) {
            System.out.println("Generic Exception -> " + exception.getMessage());
        } finally {
            return results;
        }
    }

    protected int write(String query, Map<Integer, Object> parameters) throws SQLException {
        int returnedValue = 0;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = getStatement(query, parameters, connection);
            returnedValue = preparedStatement.executeUpdate();

            // En el caso de un UPDATE o DELETE, se devuelve la cantidad de registros afectados.
            // En el caso de un INSERT, se devuelve el id generado
            if (returnedValue > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    // TODO: es posible cambiar esta estructura por una expresión ternaria
                    if (generatedKeys.next()) {
                        returnedValue = generatedKeys.getInt(1);
                    }
                } catch (Exception exception) {
                    // TODO: analizar qué ocurre si se loguea por la salida standard
                    System.out.println("No keys to be retrieved");
                }
            }
        } catch (SQLException exception) {
            // TODO: analizar qué ocurre si se loguea por la salida standard
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();// todo culpa de nacho :D
        } finally {
            //connection.close();
            return returnedValue;
        }
    }

    protected int writeTransaction (String userQuery, String userLogQuery, Map<Integer, Object> userParameters, Map<Integer, Object> userLogParameters) throws SQLException {
        int returnedValue = 0;
        int returnedValue2 = 0;
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = getStatement(userQuery, userParameters, connection);
            PreparedStatement preparedStatement2 = getStatement(userLogQuery, userLogParameters, connection);
            returnedValue = preparedStatement.executeUpdate();
            returnedValue2 = preparedStatement2.executeUpdate();

            if (returnedValue > 0 && returnedValue2 > 0) {
                connection.commit();
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        returnedValue = generatedKeys.getInt(1);
                    }
                } catch (Exception exception) {
                    System.out.println("No keys to be retrieved");
                }
            }
        } catch (SQLException exception) {
            if (connection != null) {
                System.err.print("Transaction is being rolled back");
                connection.rollback();
            }
            }catch(Exception exception){
                exception.printStackTrace();
            }finally {
            return returnedValue;
            }
    }

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
            System.out.println(exception.getMessage());
        }
        return connection;
    }

    public int saveTransaction (Map<Integer, Object> parameters) {
        return 0;
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

    public static DataAccess getDataAccess() {
        return dataAccess;
    }

    public static void setDataAccess(DataAccess dataAccess) {
        DataAccess.dataAccess = dataAccess;
    }
}

package edu.utn.dao;

import org.graalvm.compiler.lir.sparc.SPARCBlockEndOp;

import java.sql.*;
import java.util.*;

//ConnectionString = "jdbc:postgresql://192.168.33.10:5432/cuvl_db";

public  class DataAccess {

    private String connectionString;
    private String user;
    private String password;
    private Connection connection;
    private static DataAccess dataAccess;

    private DataAccess(String host, String port, String user, String password) {
        setUser(user);
        setPassword(password);
        setConnectionString("jdbc:postgresql://"+host+ ":" + port +"/cuvl_db");
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



    public static DataAccess getDataAccess(String host, String port, String user, String password){
        if(dataAccess == null)
            dataAccess = new DataAccess(host, port, user, password);

        return dataAccess;
    }


    protected List<Map<String, Object>> read(String sql) {
        return read(sql, null);
    }

    /**
     * Execute a SQL string which is expected to be use as a prepare statement together with PARAMETERS, a hashmap
     * with the parameters which will replace the wildcards.
     * @param sql
     * @param parameters
     * @return
     */
    protected List<Map<String, Object>> read(String sql, Map<Integer, Object> parameters) {

        List<Map<String, Object>> results = new ArrayList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = getStatement(sql, parameters, connection);

            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metadata = resultSet.getMetaData();

            // Mediante los metadatos, es posible obtener el nombre de la columna y el valor; si consideramos un
            // registro como un conjunto de columnas con sus valores, es posible modelarlo con un map.
            // Adicionalmente, como la consulta puede devolver varios registros, se emplea una lista para guardarlos,
            // ya que mantiene el orden y es fácil de recorrer.
            while(resultSet.next()) {
                HashMap<String, Object> columns = new HashMap<>();

                for (int i = 1; i <= metadata.getColumnCount(); ++i) {
                    columns.put(metadata.getColumnName(i), resultSet.getObject(i));
                }
                results.add(columns);
            }
            resultSet.close();

        } catch (SQLException exception) {
            // TODO: excepcion :D!
        }  catch (Exception exception) {
            // TODO: excepcion :D!
        } finally {
            // TODO: ... y la conexión dónde se cierra?
            return results;
        }
    }

    protected int write(String sql, Map<Integer, Object> parameters) {
        int returnedValue = 0;

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = getStatement(sql, parameters, connection);

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
            // TODO: excepcion :D!
        } finally {
            // TODO: ... y la conexión dónde se cierra?
            return returnedValue;
        }
    }

    private PreparedStatement getStatement(String sql, Map<Integer, Object> parameters, Connection connection) throws SQLException {
        // TODO: no todos los drivers manejan la misma configuración para larecuperación de los ids
        //String generatedColumns[] = { "ID" };
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // Al usar prepared statement se garantiza seguridad contra sql injection, pero es necesario
        // implementar una estrategia para el reemplazo de los "wildcards" por los valores deseados
        if (parameters != null) {
            Set<Integer> keys = parameters.keySet();
            for (Integer key : keys) {
                Object value = parameters.get(key);
                preparedStatement.setObject(key, value);
            }
        }

        return preparedStatement;
    }

    private Connection getConnection() {
        try {
            if (connection == null){
                connection = DriverManager.getConnection(connectionString, user, password);

                // TODO: analizar las implicancias de una transacción
                // connection.setAutoCommit(false);
            }
        } catch (SQLException exception) {
            // TODO: analizar qué ocurre si se loguea por la salida standard
            System.out.println(exception.getMessage());
        }
        return connection;
    }
}

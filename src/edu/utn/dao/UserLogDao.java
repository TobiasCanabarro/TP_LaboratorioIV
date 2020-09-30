package edu.utn.dao;

import edu.utn.entity.UserLog;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class UserLogDao extends DataAccess{

    private static final String INSERT_USER = "INSERT INTO lab.user_log (email, login, attempt_login, locked, user_id, last_login) values (?, ?, ?, ?, ?, ?)";
    private static final String INSERT_USER_LOG = "INSERT INTO lab.user_log (email, login, attempt_login, locked, user_id, last_login) values (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_LOG = "UPDATE lab.user_log set email = ?, login = ?, attempt_login = ?, locked = ?, user_id = ?, last_login = ? WHERE id = ";

    private static final String SELECT_USER_LOG = "SELECT * FROM lab.user_log WHERE email = ?";

    private static UserLogDao userLogDao;

    protected UserLogDao(String host, String port, String user, String password) {
        super(host, port, user, password);
    }

    public static UserLogDao getUserLogDao(String host, String port, String user, String password){
        if(userLogDao == null) {
            userLogDao = new UserLogDao(host, port, user, password);
        }
        return userLogDao;
    }

    public int save (Map<Integer, Object> parameters) throws SQLException {
        return write (INSERT_USER, parameters);
    }

    public int saveWithTransaction (Map<Integer, Object> userParameters, Map<Integer, Object> userLogParameters) throws SQLException {
        return writeTransaction(INSERT_USER, INSERT_USER_LOG, userParameters, userLogParameters);
    }

    public List<Map<String, Object>> get (Map<Integer, Object> parameters){
        return read (SELECT_USER_LOG, parameters);
    }

    public int update (Map<Integer, Object> parameters, long userId) throws SQLException {
        String query = UPDATE_USER_LOG + " " + userId;
        return write(query, parameters);
    }

}

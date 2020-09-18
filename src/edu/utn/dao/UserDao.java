package edu.utn.dao;

import edu.utn.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserDao extends DataAccess {

    private static UserDao userDao;

    protected UserDao(String host, String port, String user, String password){
        super(host, port, user, password);
    }

    public static UserDao getUserDao(String host, String port, String user, String password){
        if(userDao == null) {
            userDao = new UserDao(host, port, user, password);
        }
        return userDao;
    }

    public int save (Map<Integer, Object> parameters) throws SQLException {
        String query = "INSERT INTO lab.user (name, lastName, email, birthDay, nickName) values (?, ?, ?, ?, ?)";
        return write (query, parameters);
    }

    public List<Map<String, Object>> get (Map<Integer, Object> parameters){
        String query = "SELECT * FROM lab.user WHERE email = ?";
        return read (query, parameters);
    }



}

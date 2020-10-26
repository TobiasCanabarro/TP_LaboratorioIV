package edu.utn.dao;

import java.util.List;
import java.util.Map;

public class UserDao extends DataAccess {

    private static final String INSERT_USER = "INSERT INTO lab.user (name, password, surname, email, nickname, birthday) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_USER =  "SELECT * FROM lab.user WHERE email = ?";

    private static UserDao userDao;

    protected UserDao(){
        super();
    }

    public static UserDao getUserDao(){
        if(userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    public int save (Map<Integer, Object> parameters) {
        String query = INSERT_USER;
        return write (query, parameters);
    }

    public List<Map<String, Object>> get (Map<Integer, Object> parameters){
        return read (SELECT_USER, parameters);
    }

    public int update(String query, Map<Integer, Object> parameters){
        return write(query, parameters);
    }
}

package edu.utn.dao;

import edu.utn.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserDao extends DataAccess {

    private static final String INSERT_USER = "INSERT INTO lab.user (name, password, surname, email, nickname, birthday) values (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE lab.user set name = ?, password = ?, surname = ?, email = ?, nickname = ?, birthday = ? WHERE email = ";

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

    public int save (Map<Integer, Object> parameters) throws SQLException {//String name, String surname, String email,String nickname, Date birthday, int publicationId
        String query = INSERT_USER;
        return write (query, parameters);
    }

    public List<Map<String, Object>> get (Map<Integer, Object> parameters){
        String query = "SELECT * FROM lab.user WHERE email = ?";
        return read (query, parameters);
    }

    public int update (Map<Integer, Object> parameters, String email) throws SQLException {
        String query = UPDATE_USER + email;
        return write(query, parameters);
    }


}

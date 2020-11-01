package edu.utn.dao;

import java.util.List;
import java.util.Map;

public class UserDao extends DataAccess implements Dao{

    private static final String INSERT_USER = "INSERT INTO lab.user (name, password, surname, email, nickname, birthday) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_USER =  "SELECT * FROM lab.user WHERE email = ?";

    private static final String SELECT_ALL_USER = "SELECT * FROM lab.user";

    private static final String UPDATE_USER = "UPDATE lab.user set name = ?, password = ?, surname = ?, email = ?, nickname = ?, " +
            "birthday = ?, attempt_log_in = ?, log_in = ?, locked = ? WHERE email = ?";

    private static final String DELETE_USER = "UPDATE lab.user set email = ? WHERE email = ?";

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
        return write (INSERT_USER, parameters);
    }

    public List<Map<String, Object>> get (Map<Integer, Object> parameters){
        return read (SELECT_USER, parameters);
    }

    public List<Map<String, Object>> get (long id){
        String query = "SELECT * FROM lab.user WHERE id_user = " + id;
        return read(query);
    }

    public List<Map<String, Object>> getAllUser (){
        return read(SELECT_ALL_USER);
    }

    public int update(Map<Integer, Object> parameters){
        return write(UPDATE_USER, parameters);
    }

    public int delete (Map<Integer, Object> parameters){
        return write(DELETE_USER, parameters);
    }
}

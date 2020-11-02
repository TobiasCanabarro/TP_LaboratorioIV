package edu.utn.dao;

import java.util.List;
import java.util.Map;

public class UserPostDao extends DataAccess implements Dao {

    private static final String INSERT_POST = "INSERT INTO lab.user_post (id_user, date_publication, post) values (?, ?, ?)";
    private static final String SELECT_POST = "SELECT * FROM lab.user_post WHERE id_user = ?";

    private static UserPostDao userPostDao;

    protected UserPostDao(){
        super();
    }

    public static UserPostDao getUserPostDao (){
        if(userPostDao == null){
            userPostDao = new UserPostDao();
        }
        return userPostDao;
    }

    @Override
    public int save(Map<Integer, Object> parameters) {
        return write(INSERT_POST, parameters);
    }

    @Override
    public List<Map<String, Object>> get (Map<Integer, Object> parameters) {
        return read(SELECT_POST, parameters);
    }

    @Override
    public int update(Map<Integer, Object> parameters) {
        return 0;
    }

    @Override
    public int delete(Map<Integer, Object> parameters) {
        return 0;
    }
}

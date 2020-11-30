package edu.utn.mapper;

import edu.utn.dao.UserPostDao;
import edu.utn.entity.UserPost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostMapper implements Mapper <UserPost>{

    @Override
    public boolean save(UserPost post) {
        Map<Integer, Object> parameters = createParameters(post);
        UserPostDao userPostDao = UserPostDao.getUserPostDao();
        int id = userPostDao.save(parameters);
        return id != 0;
    }

    @Override
    public boolean update(UserPost post) {
        return false;
    }

    //Este metodo en esta clase no se usa.
    @Override
    public boolean delete(UserPost post) {
        return false;
    }

    public List<Map<String, Object>> get (long id){
        UserPostDao userPostDao = UserPostDao.getUserPostDao();
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(1, id);
        return userPostDao.get(parameters);
    }

    @Override
    public Map<Integer, Object> createParameters(UserPost post) {
        Map<Integer, Object> parameters = new HashMap<>();
        int i = 1;
        parameters.put(i++, post.getIdUser());
        parameters.put(i++, post.getDatePublication());
        parameters.put(i++, post.getPost());
        return parameters;
    }

    @Override
    public UserPost getEntityRecord(Map<String, Object> record) {
        return null;
    }
}

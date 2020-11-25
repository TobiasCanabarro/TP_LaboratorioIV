package edu.utn.mapper;

import edu.utn.dao.UserDao;
import edu.utn.entity.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapper implements Mapper <User>{

    @Override
    public boolean save (User user){
        Map<Integer, Object> parameters = createParameters(user);
        UserDao userDao = UserDao.getUserDao();
        int id = userDao.save(parameters);
        return id != 0;
    }

    @Override
    public boolean update(User user){
        Map<Integer, Object> parameters = createParametersForUpdate(user, user.getEmail());
        UserDao userDao = UserDao.getUserDao();
        int id = userDao.update(parameters);
        return id != 0;
    }

    //Este metodo en esta clase no se usa
    @Override
    public boolean delete(User object) {
        return false;
    }

    public User get (String email) {
        UserDao userDao = UserDao.getUserDao();
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(1, email);
        User user = null;
        List<Map<String, Object>> records = userDao.get(parameters);

        if (records.size() > 0) {
            user = getEntityRecord(records.get(0));
        }
        return user;
    }

    public User get (long id){
        UserDao userDao = UserDao.getUserDao();
        User user = null;
        List<Map<String, Object>> records = userDao.get(id);

        if (records.size() > 0) {
            user = getEntityRecord(records.get(0));
        }
        return user;
    }

    public List<User> getAllUsers (){
        UserDao userDao = UserDao.getUserDao();
        List<Map<String, Object>> records = userDao.getAllUser();
        List<User> users = new ArrayList<>();

        for (Map<String, Object> record : records) {
            users.add(getEntityRecord(record));
        }
        return users;
    }

    public Map<Integer, Object> createParameters (User user){
        int i = 1;
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(i++, user.getName());
        parameters.put(i++, user.getPassword());
        parameters.put(i++, user.getSurname());
        parameters.put(i++, user.getEmail());
        parameters.put(i++, user.getNickname());
        parameters.put(i++, user.getBirthday());
        return parameters;
    }


    private Map<Integer, Object> createParametersForUpdate (User user, String email) {
        Map<Integer, Object> parameters = createParameters(user);
        int i = parameters.size();
        parameters.put(++i, user.getAttemptLogin());
        parameters.put(++i, user.isLogIn());
        parameters.put(++i, user.isLocked());
        parameters.put(++i, email);
        return parameters;
    }

    @Override
    public User getEntityRecord(Map<String, Object> record) {
        return new User((long)record.get("id_user"), record.get("name").toString(), record.get("password").toString(),
                record.get("surname").toString(), record.get("email").toString(),
                record.get("nickname").toString(), (Date)record.get("birthday"), (int)record.get("attempt_log_in"), (boolean)record.get("log_in"), (boolean)record.get("locked"));
    }

}

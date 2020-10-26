package edu.utn.mapper;

import edu.utn.dao.UserDao;
import edu.utn.entity.User;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapper {

    public boolean save (User user){
        Map<Integer, Object> parameters = saveUserOnMapper(user);
        UserDao userDao = UserDao.getUserDao();
        int id = userDao.save(parameters);
        return id != 0;
    }

    public User get (String email) {
        UserDao userDao = UserDao.getUserDao();
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(1, email);
        User user = null;
        List<Map<String, Object>> records = userDao.get(parameters);

        if (records.size() > 0) {
            Map<String, Object> record = records.get(0);
            user = new User((long)record.get("id_user"), record.get("name").toString(), record.get("password").toString(),
                   record.get("surname").toString(), record.get("email").toString(),
                    record.get("nickname").toString(), (Date)record.get("birthday"), (int)record.get("attempt_log_in"), (boolean)record.get("log_in"), (boolean)record.get("locked"));
        }
        return user;
    }

    public boolean update(User user){
        Map<Integer, Object> parameters = saveUserOnMapper(user);
        UserDao userDao = UserDao.getUserDao();
        int id = userDao.update(parameters);
        return id != 0;
    }

    private Map<Integer, Object> saveUserOnMapper (User user) {
        int i = 1;
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(i++, user.getName());
        parameters.put(i++, user.getPassword());
        parameters.put(i++, user.getSurname());
        parameters.put(i++, user.getEmail());
        parameters.put(i++, user.getNickname());
        parameters.put(i++, user.getBirthday());
        //agregar los otros parametros del user.
        return parameters;
    }

}

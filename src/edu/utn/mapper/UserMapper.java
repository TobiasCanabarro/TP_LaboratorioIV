package edu.utn.mapper;

import edu.utn.dao.UserDao;
import edu.utn.entity.User;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserMapper {

    String CONNECTION_STRING = "jdbc:postgresql://192.168.33.10:5432/cuvl_db";

    public boolean save (User user) throws SQLException {
        int i = 1;

        Map<Integer, Object> parameters = new HashMap<>(); // lo tiene que hacer UserDto
        parameters.put(i++, user.getName());
        parameters.put(i++, user.getLastName());
        parameters.put(i++, user.getEmail());
        parameters.put(i++, user.getBirthday());
        parameters.put(i++, user.getNickName());

        UserDao userDao = UserDao.getUserDao("192.168.33.10", "5438", "cuvl", "cuvl1234");
        int id = userDao.save(parameters);

        return id != 0;
    }

    public User get (String id) {
        UserDao userDao = UserDao.getUserDao("192.168.33.10", "5438", "cuvl", "cuvl1234");
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(1, id);
        User user = null;

        List<Map<String, Object>> records = userDao.get(parameters);

        if (records.size() > 0) {
            Map<String, Object> record = records.get(0); //String name, String lastName, String nickName, String email, Date birthday
            user = new User(record.get("name").toString() , record.get("lastname").toString(), record.get("email").toString(),
                    (Date)record.get("birthday"), record.get("nickname").toString());
        }
        return user;
    }
}

package edu.utn.mapper;

import edu.utn.dao.UserDao;
import edu.utn.entity.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class UserMapper {

    String CONN_STRINGs = "jdbc:postgresql://192.168.33.10:5432/cuvl_db";

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
}

package edu.utn.mapper;

import edu.utn.dao.UserDao;
import edu.utn.dto.UserDto;
import edu.utn.entity.User;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserMapper {

    String CONNECTION_STRING = "jdbc:postgresql://192.168.33.10:5432/cuvl_db";

    public boolean save (User user) throws SQLException {
        int i = 1;
        UserDto userDto = new UserDto();
        Map<Integer, Object> parameters = userDto.saveUserOnMapper(user); // lo tiene que hacer UserDto
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
            Map<String, Object> record = records.get(0);//int id, String name, String password, String surname, String email, String nickname, Date birthday, int publicationId
            user = new User((long)record.get("id"), record.get("name").toString(), record.get("password").toString(),
                    record.get("surname").toString(), record.get("email").toString(), record.get("nickname").toString(),
                    (Date)record.get("birthday"), (long)record.get("publication_id"));
        }
        return user;
    }
}

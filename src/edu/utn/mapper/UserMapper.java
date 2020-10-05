package edu.utn.mapper;

import edu.utn.dao.UserDao;
import edu.utn.dto.UserDto;
import edu.utn.dto.UserLogDto;
import edu.utn.entity.User;
import edu.utn.validator.SQLValidator;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserMapper implements Mapper {

    private User user;
    String CONNECTION_STRING = "jdbc:postgresql://192.168.33.10:5432/cuvl_db";

    public UserMapper (User user){
        setUser(user);
    }

    public boolean save () throws SQLException {
        UserDto userDto = new UserDto();
        Map<Integer, Object> parameters = userDto.saveUserOnMapper(getUser()); // lo tiene que hacer UserDto
        UserDao userDao = UserDao.getUserDao();
        int id = userDao.save(parameters);
        return id != 0;
    }

    public User get (String id) {
        UserDao userDao = UserDao.getUserDao();
        Map<Integer, Object> parameters = new HashMap<>();
        SQLValidator validator = new SQLValidator();
        parameters.put(1, id);
        User user = null;
        List<Map<String, Object>> records = userDao.get(parameters);

        if (records.size() > 0) {
            Map<String, Object> record = records.get(0);//int id, String name, String password, String surname, String email, String nickname, Date birthday, int publicationId
            user = new User((long)record.get("id"), record.get("name").toString(), record.get("password").toString(),
                   record.get("surname").toString(), record.get("email").toString(),
                    record.get("nickname").toString(), (Date)record.get("birthday"), validator.publicationIdIsNull(record, "publication_id"));
        }
        return user;
    }

    public boolean update () throws SQLException {
        UserDto userDto = new UserDto();
        Map<Integer, Object> parameters = userDto.saveUserOnMapper(getUser());
        UserDao userDao = UserDao.getUserDao();
        int id = userDao.update(parameters, getUser().getEmail());
        return id != 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

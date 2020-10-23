package edu.utn.mapper;

import edu.utn.dao.UserDao;
import edu.utn.entity.User;
import edu.utn.validator.PublicationValidator;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserMapper implements Mapper {

    private User user;

    public UserMapper (User user){
        setUser(user);
    }

    public boolean save () throws SQLException {
        Map<Integer, Object> parameters = saveUserOnMapper(getUser());
        UserDao userDao = UserDao.getUserDao();
        int id = userDao.save(parameters);
        return id != 0;
    }

    public User get (String id) {
        UserDao userDao = UserDao.getUserDao();
        Map<Integer, Object> parameters = new HashMap<>();
        PublicationValidator validator = new PublicationValidator();
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
        Map<Integer, Object> parameters = saveUserOnMapper(getUser(), getUser().getEmail());
        UserDao userDao = UserDao.getUserDao();
        //int id = userDao.update(parameters, getUser().getEmail());
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
        return parameters;
    }

    private Map<Integer, Object> saveUserOnMapper (User user, String email) {
        int i = 1;
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(i++, user.getName());
        parameters.put(i++, user.getPassword());
        parameters.put(i++, user.getSurname());
        parameters.put(i++, user.getEmail());
        parameters.put(i++, user.getNickname());
        parameters.put(i++, user.getBirthday());
        parameters.put(i++, email);
        return parameters;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

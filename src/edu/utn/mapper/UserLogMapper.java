package edu.utn.mapper;

import edu.utn.dao.UserLogDao;
import edu.utn.dto.UserLogDto;
import edu.utn.entity.UserLog;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserLogMapper implements Mapper{

    private UserLog user;

    public UserLogMapper(UserLog user) {
        setUser(user);
    }

    public UserLogMapper (){}

    public boolean save () throws SQLException {
        UserLogDto userLogDto = new UserLogDto();
        Map<Integer, Object> parameters = userLogDto.saveUserLogInMapper(getUser());//

        UserLogDao userLogDao = UserLogDao.getUserLogDao("192.168.33.10", "5438", "cuvl", "cuvl1234");
        int id = userLogDao.save(parameters);
        return id != 0;
    }

    public UserLog get () {

        UserLogDao userLogDao = UserLogDao.getUserLogDao("192.168.33.10", "5438", "cuvl", "cuvl1234");
        Map<Integer, Object> parameters = new HashMap<>();
        UserLogDto userLogDto = new UserLogDto();
        parameters.put(1, getUser().getEmail());
        UserLog userLog = null;

        List<Map<String, Object>> records = userLogDao.get(parameters);
        if (records.size() > 0) {
            userLog = userLogDto.getUserLog(records.get(0));
        }
        return userLog;
    }


    public boolean update () throws SQLException {
        UserLogDto userLogDto = new UserLogDto();
        Map<Integer,Object> parameters = userLogDto.saveUserLogInMapper(getUser());
        UserLogDao userLogDao = UserLogDao.getUserLogDao("192.168.33.10", "5438", "cuvl", "cuvl1234");
        int id = userLogDao.update(parameters, getUser().getId());
        return id != 0;
    }

//    private Map<Integer, Object> saveUserLogInMapper(){
//        int i = 1;
//        Map<Integer, Object> parameters = new HashMap<>();
//        parameters.put(i++, user.getName());
//        parameters.put(i++, user.getPassword());
//        parameters.put(i++, user.getSurname());
//        parameters.put(i++, user.getEmail());
//        parameters.put(i++, user.getNickname());
//        parameters.put(i++, user.getBirthday());
//        return parameters;
//    }

    public UserLog getUser() {
        return user;
    }

    public void setUser(UserLog user) {
        this.user = user;
    }
}

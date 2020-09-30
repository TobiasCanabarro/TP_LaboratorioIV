package edu.utn.mapper;

import edu.utn.dao.UserLogDao;
import edu.utn.dto.UserDto;
import edu.utn.dto.UserLogDto;
import edu.utn.entity.User;
import edu.utn.entity.UserLog;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserLogMapper {

    public boolean save (UserLog user) throws SQLException {
        UserLogDto userLogDto = new UserLogDto();
        Map<Integer, Object> parameters = userLogDto.saveUserLogInMapper(user);

        UserLogDao userLogDao = UserLogDao.getUserLogDao("192.168.33.10", "5438", "cuvl", "cuvl1234");
        int id = userLogDao.save(parameters);
        return id != 0;
    }

    public boolean saveWithTransaction (User user, UserLog userLog) throws SQLException {
        UserLogDto userLogDto = new UserLogDto();
        UserDto userDto = new UserDto();

        Map<Integer, Object> userParameters = userDto.saveUserOnMapper(user);
        Map<Integer, Object> userLogParameters = userLogDto.saveUserLogInMapper(userLog);

        UserLogDao userLogDao = UserLogDao.getUserLogDao("192.168.33.10", "5438", "cuvl", "cuvl1234");
        int id = userLogDao.saveWithTransaction(userParameters, userLogParameters);
        return id != 0;
    }


    public UserLog get (String id) {

        UserLogDao userLogDao = UserLogDao.getUserLogDao("192.168.33.10", "5438", "cuvl", "cuvl1234");
        Map<Integer, Object> parameters = new HashMap<>();
        UserLogDto userLogDto = new UserLogDto();
        parameters.put(1, id);
        UserLog userLog = null;

        List<Map<String, Object>> records = userLogDao.get(parameters);
        if (records.size() > 0) {
            userLog = userLogDto.getUserLog(records.get(0));
        }
        return userLog;
    }


    public boolean update (UserLog user) throws SQLException {
        UserLogDto userLogDto = new UserLogDto();
        Map<Integer,Object> parameters = userLogDto.saveUserLogInMapper(user);
        UserLogDao userLogDao = UserLogDao.getUserLogDao("192.168.33.10", "5438", "cuvl", "cuvl1234");
        int id = userLogDao.update(parameters, user.getId());
        return id != 0;
    }


}

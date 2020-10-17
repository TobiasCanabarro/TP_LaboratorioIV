package edu.utn.mapper;

import edu.utn.dao.UserLogDao;
import edu.utn.dto.UserLogDto;
import edu.utn.entity.UserLog;

import java.sql.Date;
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
        Map<Integer, Object> parameters = saveUserLogInMapper();
        UserLogDao userLogDao = UserLogDao.getUserLogDao();
        int id = userLogDao.save(parameters);
        return id != 0;
    }

    public UserLog get () {

        UserLogDao userLogDao = UserLogDao.getUserLogDao();
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(1, getUser().getEmail());
        UserLog userLog = null;

        List<Map<String, Object>> records = userLogDao.get(parameters);
        if (records.size() > 0) {
            userLog = convertToUserLog(records.get(0));
        }
        return userLog;
    }


    public boolean update () throws SQLException {
        Map<Integer, Object> parameters = saveUserLogInMapper();
        UserLogDao userLogDao = UserLogDao.getUserLogDao();
        int id = userLogDao.update(parameters, getUser().getId());
        return id != 0;
    }

    private Map<Integer, Object> saveUserLogInMapper (){
        int i = 1;
        Map<Integer, Object> userLogParameters = new HashMap<>();
        userLogParameters.put(i++, getUser().getEmail());
        userLogParameters.put(i++, getUser().isLogin());
        userLogParameters.put(i++, getUser().getAttemptLogin());
        userLogParameters.put(i++, getUser().isLocked());
        userLogParameters.put(i++, getUser().getUserId());
        userLogParameters.put(i++, getUser().getLastLogin());
        return userLogParameters;
    }

    private UserLog convertToUserLog (Map<String, Object> record) {
        return new UserLog((long)record.get("id"), record.get("email").toString(), (boolean)record.get("login"),
                    (int)record.get("attempt_login"), (boolean)record.get("locked"), (long)record.get("user_id"),
                    (Date)record.get("last_login"));
    }

    public UserLog getUser() {
        return user;
    }

    public void setUser(UserLog user) {
        this.user = user;
    }
}

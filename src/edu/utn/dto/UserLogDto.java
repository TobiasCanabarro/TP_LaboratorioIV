package edu.utn.dto;

import edu.utn.entity.UserLog;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class UserLogDto {

    public Map<Integer, Object> saveUserLogInMapper (UserLog userLog){
        int i = 1;
        Map<Integer, Object> userLogParameters = new HashMap<>(); //String email, boolean login, int attemptLogin, boolean locked, long userId, Date lastLogin
        userLogParameters.put(i++, userLog.getEmail());
        userLogParameters.put(i++, userLog.isLogin());
        userLogParameters.put(i++, userLog.getAttemptLogin());
        userLogParameters.put(i++, userLog.isLocked());
        userLogParameters.put(i++, userLog.getUserId());
        userLogParameters.put(i++, userLog.getLastLogin());
        return userLogParameters;
    }

    public UserLog getUserLog (Map<String, Object> record) {
        return new UserLog((long)record.get("id"), record.get("email").toString(), (boolean)record.get("login"),
                (int)record.get("attempt_login"), (boolean)record.get("locked"), (long)record.get("user_id"),
                (Date)record.get("last_login"));
    }

}

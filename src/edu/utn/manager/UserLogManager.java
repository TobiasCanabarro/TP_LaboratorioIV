package edu.utn.manager;


import edu.utn.entity.User;
import edu.utn.entity.UserLog;
import edu.utn.exception.EmailException;
import edu.utn.mapper.UserLogMapper;
import edu.utn.validator.UserLogValidator;

import java.sql.Date;
import java.sql.SQLException;

public class UserLogManager {

    private static final int INITIAL_ATTEMPT = 0;
    private UserLogMapper userLogMapper;

    public UserLogManager (UserLogMapper userLogMapper) {
        setUserLogMapper(userLogMapper);
    }

    public boolean save (UserLog userLog) {
        UserLogValidator validator = new UserLogValidator();
        boolean success = false;
        try {
            validator.isValid(userLog);
            success = getUserLogMapper().save();

        }catch (EmailException exception){
            System.out.printf(exception.getMessage());
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        finally {
            return success;
        }
    }

    public UserLog get () {
        return getUserLogMapper().get();
    }

    public boolean update (){
        boolean success = false;
        try {
            success = getUserLogMapper().update();

        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        finally {
            return success;
        }
    }

    public void lockUser (UserLog user){
        user.setAttemptLogin(INITIAL_ATTEMPT);
        user.setLocked(true);
    }

    public java.sql.Date generateCurrentDate (java.util.Date date) {
        Date loginDate = new Date(date.getYear(), date.getMonth(), date.getDay());
        return loginDate;
    }

    public UserLog createUserLog (User user) {
        UserLog log = new UserLog(user.getEmail(), user.getId(), generateCurrentDate(new java.util.Date()));
        getUserLogMapper().setUser(log);
        save(log);
        return log;
    }

    public long getIdLog (UserLog log) {
        UserLog logId = get();
        return logId.getId();
    }

    private UserLog getUserLog(){
        return getUserLogMapper().getUser();
    }

    public UserLogMapper getUserLogMapper() {
        return userLogMapper;
    }

    public void setUserLogMapper(UserLogMapper userLogMapper) {
        this.userLogMapper = userLogMapper;
    }
}

package edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.entity.UserLog;
import edu.utn.exception.EmailException;
import edu.utn.log.LogHelper;
import edu.utn.mapper.UserLogMapper;
import edu.utn.validator.UserLogValidator;

import java.sql.SQLException;

public class UserLogManager implements Manager{

    private static final int INITIAL_ATTEMPT = 0;
    private UserLogMapper userLogMapper;

    public UserLogManager (UserLogMapper userLogMapper) {
        setUserLogMapper(userLogMapper);
    }

    public boolean save () {
        UserLogValidator validator = new UserLogValidator();
        boolean success = false;
        try {
            validator.isValid(getUserLog());
            success = getUserLogMapper().save();

        }catch (EmailException ex){
            LogHelper.createNewLog(ex.getMessage());
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
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

        }catch (SQLException ex){
            LogHelper.createNewLog(ex.getMessage());
        }
        finally {
            return success;
        }
    }

    public void lockUser (UserLog user){
        user.setAttemptLogin(INITIAL_ATTEMPT);
        user.setLocked(true);
    }

    public UserLog createUserLog (User user) {
        UserLog log = new UserLog(user.getEmail(), user.getId());
        getUserLogMapper().setUser(log);
        save();
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

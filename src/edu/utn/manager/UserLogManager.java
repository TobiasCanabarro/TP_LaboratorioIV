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
            success = getUserLogMapper().save(userLog);

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

    //Puede que la trasanccion solo vaya con el userLog. Ya que se necesita el ID del user; se le asigna recien en la DB
    public boolean saveWithTransaction (User user) {
        UserLog userLog = new UserLog(user.getEmail(), 4, new Date(8888));//numeros magicos
        UserLogValidator validator = new UserLogValidator();
        boolean success = false;
        try {
            validator.isValid(userLog);
            success = getUserLogMapper().saveWithTransaction(user, userLog);

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

    public UserLog get (String id) {
        return getUserLogMapper().get(id);
    }

    public boolean update (UserLog user) throws SQLException {
        boolean success = false;
        try {
            success = getUserLogMapper().update(user);

        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        finally {
            return success;
        }
    }

    public void lockUser (UserLog user) throws SQLException {
        user.setAttemptLogin(INITIAL_ATTEMPT);
        user.setLocked(true);
    }

    public java.sql.Date generateCurrentDate (java.util.Date date) {
        return new Date(date.getYear(), date.getMonth(), date.getDay());
    }

    public UserLogMapper getUserLogMapper() {
        return userLogMapper;
    }

    public void setUserLogMapper(UserLogMapper userLogMapper) {
        this.userLogMapper = userLogMapper;
    }
}

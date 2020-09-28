package edu.utn.entity;

import edu.utn.manager.UserLogManager;
import edu.utn.manager.UserManager;
import edu.utn.mapper.UserLogMapper;
import edu.utn.validator.SQLValidator;
import edu.utn.validator.UserValidator;

import java.sql.SQLException;
import java.util.Date;

public class LogIn {

    private UserManager userManager;

    public LogIn (UserManager userManager){
        setUserManager(userManager);
    }

    public boolean logIn (User user) {
        SQLValidator validator = new SQLValidator();
        UserValidator userValidator = new UserValidator();
        UserLogManager userLogManager = new UserLogManager(new UserLogMapper());
        UserLog userLog = new UserLog(user.getEmail(), user.getId(),userLogManager.generateCurrentDate(new Date()));//el id del user que se loggea es 0
        User found = null;
        boolean value = validator.existsUser(getUserManager(), user);

        if(value){
            try{
                found = getUserManager().get(user.getEmail());
                value &= userValidator.equalPassword(found, user);
                if(!value) {
                    userLogManager.lockUser(userLog);
                }else {
                    userLog.setLogin(true);
                }
                userLog.setUserId(found.getId());
                userLogManager.update(userLog);
            }catch (SQLException exception){
                System.out.println(exception.getMessage());
            }

        }
        return value;
    }

    public boolean logOut () {
        //TODO hace un UPDATE en la tabla user_log del campo login
        return true;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}

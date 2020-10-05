package edu.utn.entity;

import edu.utn.manager.UserLogManager;
import edu.utn.manager.UserManager;
import edu.utn.mapper.UserLogMapper;
import edu.utn.validator.SQLValidator;
import edu.utn.validator.UserValidator;

import java.sql.SQLException;

public class LogIn {

    private UserManager userManager;
    private UserLogManager userLogManager;

    public LogIn (UserManager userManager){
        setUserManager(userManager);
    }

    public LogIn (UserManager userManager, UserLogManager userLogManager){
        setUserManager(userManager);
        setUserLogManager(userLogManager);
    }

    public boolean logIn () {
        SQLValidator validator = new SQLValidator();
        UserValidator userValidator = new UserValidator();
        UserLogManager userLogManager = new UserLogManager(new UserLogMapper());
        boolean value = validator.existsUser(getUserManager(), getUser());//Puede que esto este de mas...
        User userFound = null;

        if(value){
            try{
                userFound = getUserManager().get();
                UserLog log = userLogManager.createUserLog(userFound);//Se crea un user log de usuario que se quiere loggear
                userLogManager.getUserLogMapper().setUser(log);
                log.setId(userLogManager.getIdLog(log));//Obtiene el ID genererado por la DB del log

                value &= userValidator.equalPassword(userFound, getUser(), log);//Prueba
                if(!value) {
                    userLogManager.lockUser(log);
                }else {
                    log.setLogin(true);
                }
                userLogManager.getUserLogMapper().setUser(log);// Le asigna al mapper el log que se va a usar
                userLogManager.update();
            }catch (SQLException exception){
                System.out.println(exception.getMessage());
            }catch (NullPointerException exception){
                System.out.println("El usuario no existe!");
            }
        }
        return value;
    }

    //TODO hace un UPDATE en la tabla user_log del campo login
    public boolean logOut () {
        UserLog log = getUserLogManager().get();
        boolean value = false;
        log.setLogin(false);
        setUserLog(log);
        try {
            value = getUserLogManager().update();
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        return value;
    }

    private UserLog getUserLog(){
        return getUserLogManager().getUserLogMapper().getUser();
    }

    private void setUserLog (UserLog userLog) {
        getUserLogManager().getUserLogMapper().setUser(userLog);
    }

    private User getUser() {
        return getUserManager().getUserMapper().getUser();
    }

    public UserLogManager getUserLogManager() {
        return userLogManager;
    }

    public void setUserLogManager(UserLogManager userLogManager) {
        this.userLogManager = userLogManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}

package edu.utn.entity;

import edu.utn.manager.UserLogManager;
import edu.utn.manager.UserManager;
import edu.utn.mapper.UserLogMapper;
import edu.utn.validator.SQLValidator;
import edu.utn.validator.UserValidator;

import java.sql.SQLException;

public class LogIn {

    private UserManager userManager;

    public LogIn (UserManager userManager){
        setUserManager(userManager);
    }

    public boolean logIn (User user) {
        SQLValidator validator = new SQLValidator();
        UserValidator userValidator = new UserValidator();
        UserLogManager userLogManager = new UserLogManager(new UserLogMapper());
        boolean value = validator.existsUser(getUserManager(), user);//Puede que esto este de mas...
        User userFound = null;

        if(value){
            try{
                userFound = getUserManager().get(user.getEmail());
                UserLog log = userLogManager.createUserLog(userFound);//Se crea un user log de usuario que se quiere loggear
                log.setId(userLogManager.getIdLog(log));//Obtiene el ID genererado por la DB del log

                value &= userValidator.equalPassword(userFound, user, log);//Prueba
                if(!value) {
                    userLogManager.lockUser(log);
                }else {
                    log.setLogin(true);
                }
                userLogManager.update(log);
            }catch (SQLException exception){
                System.out.println(exception.getMessage());
            }catch (NullPointerException exception){
                System.out.println("El usuario no existe!");
            }
        }
        return value;
    }

    //TODO hace un UPDATE en la tabla user_log del campo login
    public boolean logOut (User user) {
        UserLogManager userLogManager = new UserLogManager(new UserLogMapper());
        UserLog log = userLogManager.get(user.getEmail());
        log.setLogin(false);
        try {
            boolean value = userLogManager.update(log);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }


        return true;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}

package edu.utn.entity;

import edu.utn.factory.UserLogManagerFactory;
import edu.utn.factory.UserManagerFactory;
import edu.utn.log.LogHelper;
import edu.utn.manager.UserLogManager;
import edu.utn.manager.UserManager;
import edu.utn.mapper.UserLogMapper;
import edu.utn.validator.UserValidator;

public class LogIn {

    public boolean logIn (User user) {
        UserValidator userValidator = new UserValidator();
        UserLogManager userLogManager = new UserLogManager(new UserLogMapper());
        UserManager userManager = UserManagerFactory.create(user);
        User userFound = userManager.get();
        boolean value = userValidator.canLogin(userFound);

        if(value){
            try{
                UserLog log = userLogManager.createUserLog(userFound);//Se crea un user log de usuario que se quiere loggear
                userLogManager.getUserLogMapper().setUser(log);
                log.setId(userLogManager.getIdLog(log));//Obtiene el ID genererado por la DB del log

                value &= userValidator.equalPassword(userFound, getUser(userManager), log);//Prueba
                if(!value) {
                    userLogManager.lockUser(log);
                }else {
                    log.setLogin(true);
                }
                userLogManager.getUserLogMapper().setUser(log);// se Le asigna al mapper el log que se va a persistir en DB
                userLogManager.update();
            }catch (NullPointerException ex){
                LogHelper.createNewLog("El usuario no existe!");
            }
        }
        return value;
    }

    public boolean logOut (User user) {
        UserLog userLog = new UserLog(user.getEmail(), user.getId());
        UserLogManager userLogManager = UserLogManagerFactory.create(userLog);
        UserLog logFound = userLogManager.get();
        boolean value = false;
        logFound.setLogin(false);
        setUserLog(userLogManager, logFound);
        try {
            value = userLogManager.update();
        }catch (Exception ex){
            LogHelper.createNewLog(ex.getMessage());
        }

        return value;
    }

    private void setUserLog (UserLogManager userLogManager, UserLog userLog) {
        userLogManager.getUserLogMapper().setUser(userLog);
    }

    private User getUser(UserManager manager) {
        return manager.getUserMapper().getUser();
    }

}

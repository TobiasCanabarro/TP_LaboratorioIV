package edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.factory.UserManagerFactory;
import edu.utn.log.LogHelper;
import edu.utn.manager.UserManager;
import edu.utn.validator.UserValidator;

public class LogInManager {

    /*
    public boolean logIn (User user) {
        UserValidator userValidator = new UserValidator();
        UserLogManager userLogManager = new UserLogManager(new UserLogMapper());
        UserManager userManager = UserManagerFactory.create(user);
        User userFound = userManager.get();
        boolean value = userValidator.canLogin(userFound);

        if(value){
            try{
                UserState log = userLogManager.createUserLog(userFound);//Se crea un user log de usuario que se quiere loggear
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
        UserState userState = new UserState(user.getEmail(), user.getId());
        UserLogManager userLogManager = UserLogManagerFactory.create(userState);
        UserState logFound = userLogManager.get();
        boolean value = false;
        try {
            logFound.setLogin(false);
            setUserLog(userLogManager, logFound);
            value = userLogManager.update();
        }catch (Exception ex){
            LogHelper.createNewLog(ex.getMessage());
        }

        return value;
    }

    private void setUserLog (UserLogManager userLogManager, UserState userState) {
        userLogManager.getUserLogMapper().setUser(userState);
    }

    private User getUser(UserManager manager) {
        return manager.getUserMapper().getUser();
    }
    */
}

package edu.utn.entity;

import edu.utn.manager.UserManager;
import edu.utn.validator.SQLValidator;

public class SignIn {

    private UserManager userManager;

    public SignIn (UserManager manager) {
        setUserManager(manager);
    }

    public boolean signIn () {
        SQLValidator validator = new SQLValidator(); //TODO agregar excepcion de usuario existente
        boolean value = validator.existsUser(getUserManager(), getUser());
        boolean success = false;
        if(!value) {
            try {
                success = getUserManager().save();
            }catch (Exception e){
                System.out.println(e);
            }
        }
//        if(success){//TODO hacer un insert en la tabla user_log
//            try {
//                UserLogManager userLogManager  = new UserLogManager(new UserLogMapper());
//                success = userLogManager.saveWithTransaction(user, new UserLog(user.getEmail(), user.getId(), new Date(99999)));
//            }catch (Exception e){
//                System.out.println(e.getMessage());
//            }
//        }
        return success;
    }


    private User getUser (){
        return getUserManager().getUserMapper().getUser();
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}

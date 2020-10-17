package edu.utn.entity;

import edu.utn.factory.UserManagerFactory;
import edu.utn.log.LogHelper;
import edu.utn.manager.UserManager;
import edu.utn.validator.UserValidator;

public class SignIn {

    public boolean signIn (User user) {
        UserValidator validator = new UserValidator();
        UserManager userManager = UserManagerFactory.create(user);
        boolean value = validator.existsUser(getUser(userManager));
        boolean success = false;
        if(!value) {
            try {
                success = userManager.save();
            }catch (Exception ex){
                LogHelper.createNewLog(ex.getMessage());
            }
        }
//        if(success){
//            try {
//                UserLogManager userLogManager  = new UserLogManager(new UserLogMapper());
//                success = userLogManager.saveWithTransaction(user, new UserLog(user.getEmail(), user.getId(), new Date(99999)));
//            }catch (Exception e){
//                System.out.println(e.getMessage());
//            }
//        }
        return success;
    }


    private User getUser (UserManager manager){
        return manager.getUserMapper().getUser();
    }

}

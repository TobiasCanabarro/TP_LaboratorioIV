package edu.utn.validator;

import edu.utn.entity.User;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.UserManager;

public class UserValidator extends Validator {

    private static final int MAX_ATTEMPT = 3;

    public boolean isValidUser (User user){
        boolean value = isValidName(user);
        value &= isValidSurname(user);
        value &= isValidEmail(user.getEmail());
        value &= isValidPassword(user.getPassword());
        return value;
    }

    private boolean isValidName (User user){
        boolean value = user.getName() != null && !user.getName().isEmpty();
        value &= insideAlphabet(user.getName());
        return value;
    }

    private boolean isValidSurname (User user) {
        boolean value = user.getSurname() != null && !user.getSurname().isEmpty();
        value &= insideAlphabet(user.getName());
        return value;
    }

//    //TODO sacar el while, reeplzarlo por un if
//    //INITIAL attemp se tiene que traer de la tabla
//    public boolean equalPassword (User userFound, String passwordLogIn){
//        UserManager manager = UserManagerFactory.create();
//        User userFound = manager.get(userFound.getEmail());
//        boolean value = false;
//        int attempt = 0;
//        if (attempt < MAX_ATTEMPT){
//            if(userFound.getPassword().equals(passwordLogIn)) {
//                value = true;
//            }
//            else {
//                attempt++;
//                userFound.setAttemptLogin(attempt);
//                manager.updateAttempt(userFound.getEmail(), attempt);
//            }
//            //Llamada front
//        }
//        return value;
//    }

    public boolean isValidPassword (String password){
        boolean value =  password != null && !password.isEmpty();
        value &= isAlphaNumeric(password);
        return value;
    }

    public boolean canLogin (User user) {
        if(user == null){
            return false;
        }
        boolean value = existsUser(user.getEmail());
        value &= !alreadyLoggedIn(user.getEmail());
        return  value;
    }

    public  boolean existsUser (String email) {
        UserManager manager = UserManagerFactory.create();
        User found = manager.get(email); //key para buscar el registro. Esta hardcodeado el email para la busqueda
        return found != null;
    }

    public boolean alreadyLoggedIn (String email) {
       boolean value = true;
       UserManager manager = UserManagerFactory.create();
       User userFound = manager.get(email);
       if(userFound == null){
           value = false;
       }else {
           value &= userFound.isLogIn();
       }
       return value;
    }

    public boolean isLocked (String email) {
        UserManager manager = UserManagerFactory.create();
        User found = manager.get(email);
        if(found == null){
            return false;
        }
        return found.isLocked();
    }


}

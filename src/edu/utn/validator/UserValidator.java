package edu.utn.validator;

import edu.utn.entity.User;
import edu.utn.entity.UserLog;
import edu.utn.exception.EmailException;
import edu.utn.exception.PasswordException;
import edu.utn.exception.SurNameException;
import edu.utn.exception.NameException;
import edu.utn.factory.UserLogManagerFactory;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.UserLogManager;
import edu.utn.manager.UserManager;
import edu.utn.mapper.UserLogMapper;

public class UserValidator extends Validator {

    private static final String NAME_EXCEPTION = "NAME EXCEPTION";
    private static final String SURNAME_EXCEPTION = "SURNAME EXCEPTION";
    private static final String PASSWORD_EXCEPTION = "PASSWORD EXCEPTION";
    private static final int MAX_ATTEMPT = 3;

    public void isValid (User user) throws NameException, SurNameException, EmailException, PasswordException {
        isValidName(user);
        isValidSurname(user);
        isValidEmail(user.getEmail());
        isValidPassword(user);
    }

    private void isValidName (User user) throws NameException {
        boolean value = user.getName() != null && !user.getName().isEmpty();
        value &= insideAlphabet(user.getName());
        if (!value) {
            throw new NameException(NAME_EXCEPTION);
        }
    }

    private void isValidSurname (User user) throws SurNameException {
        boolean value = user.getSurname() != null && !user.getSurname().isEmpty();
        value &= insideAlphabet(user.getName());
        if (!value) {
            throw new SurNameException(SURNAME_EXCEPTION);
        }
    }

    //TODO Puede que lo tenga que hacer el front ?
    public boolean equalPassword (User userFound, User userLogIn, UserLog log){
        UserLogManager manager = new UserLogManager(new UserLogMapper(log));
        boolean value = false;

        int attempt = 0;
        while (attempt < MAX_ATTEMPT && !value){
            if(userFound.getPassword().equals(userLogIn.getPassword())) {
                value = true;
            }
            else {
                attempt++;
                log.setAttemptLogin(attempt);
                manager.getUserLogMapper().setUser(log);
                manager.update();
            }
        }
        return value;
    }

    public void isValidPassword (User user) throws PasswordException {
        boolean value =  user.getPassword() != null && !user.getPassword().isEmpty();
        value &= isAlphaNumeric(user.getPassword());
        if(!value){
            throw new PasswordException(PASSWORD_EXCEPTION);
        }
    }

    public boolean canLogin (User user) {
        if(user == null){
            return false;
        }
        boolean value = existsUser(user);
        value &= !alreadyLoggedIn(user);
        return  value;
    }

    public  boolean existsUser (User user) {
        UserManager manager = UserManagerFactory.create(user);
        User found = manager.get(); //key para buscar el registro. Esta hardcodeado el email para la busqueda
        if (found != null) {
            return found.getEmail().equals(user.getEmail());
        }
        return false;
    }

    public boolean alreadyLoggedIn (User user) {
       UserLog log = new UserLog(user.getEmail(), user.getId());
       UserLogManager userLogManager = UserLogManagerFactory.create(log);
       boolean value = true;
       UserLog userFound = userLogManager.get();
       if(userFound == null){
           value = false;
       }else {
           value &= userFound.isLogin();
       }
       return value;
    }

}

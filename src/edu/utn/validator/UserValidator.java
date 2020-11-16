package edu.utn.validator;

import edu.utn.entity.User;
import edu.utn.enums.Result;
import edu.utn.factory.UserManagerFactory;
import edu.utn.log.LogHelper;
import edu.utn.mail.Mail;
import edu.utn.manager.UserManager;

import javax.mail.MessagingException;

public class UserValidator extends Validator <User> {

    private static final int MAX_ATTEMPT = 4;

    //
    public boolean isValidUser (User user){
        boolean value = isValidName(user);
        value &= isValidSurname(user);
        value &= isValidEmail(user.getEmail());
        value &= isValidPassword(user.getPassword());
        if(value) setLoweCase(user);
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

    private void setLoweCase (User user){
        user.setName(user.getName().toLowerCase());
        user.setSurname(user.getSurname().toLowerCase());
    }

    public boolean isValidPassword (String password){
        boolean value =  password != null && !password.isEmpty();
        value &= isAlphaNumeric(password);
        return value;
    }

    public  boolean existsUser (String email) {
        UserManager manager = UserManagerFactory.create();
        User found = manager.get(email); //key para buscar el registro. Esta hardcodeado el email para la busqueda
        return found != null;
    }

    public boolean attemptsRemain(User user) {
        boolean value = user.getAttemptLogin() <= MAX_ATTEMPT;
        if(!value){
            user.setLocked(true);
            value &= Mail.sendMail(user.getEmail(), Result.LOCKED_ACCOUNT, "Account Locked!");
        }
        return value;
    }

    public boolean isLocked (User user) {
        return user.isLocked();
    }

    public boolean isLogIn (User user){
        return user.isLogIn();
    }

}

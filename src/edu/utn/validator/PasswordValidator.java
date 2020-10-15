package edu.utn.validator;

import edu.utn.exception.PasswordException;

public class PasswordValidator extends Validator {

    private static final String PASSWORD_EXCEPTION = "PASSWORD EXCEPTION";

    public boolean  newPasswordIsValid (String oldPassword, String newPassword) throws PasswordException {
        boolean value = newPassword.isEmpty();
        value &= isAlphaNumeric(newPassword);
        value &= oldPassword.equals(newPassword);
        if(!value){
            throw new PasswordException(PASSWORD_EXCEPTION);
        }
        return value;
    }



}

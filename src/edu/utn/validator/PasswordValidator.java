package edu.utn.validator;


public class PasswordValidator extends Validator {


    public boolean  newPasswordIsValid (String oldPassword, String newPassword) {
        boolean value = !newPassword.isEmpty();
        value &= isAlphaNumeric(newPassword);
        value &= !oldPassword.equals(newPassword);
        return value;
    }




}

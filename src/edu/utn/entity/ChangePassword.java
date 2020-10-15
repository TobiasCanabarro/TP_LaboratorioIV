package edu.utn.entity;

import edu.utn.exception.PasswordException;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.UserManager;
import edu.utn.validator.PasswordValidator;

public class ChangePassword {



    public boolean changePassword (User user, String newPassword) throws PasswordException {
        PasswordValidator validator = new PasswordValidator();
        boolean value = validator.newPasswordIsValid(user.getPassword(), newPassword);//En caso que no este en forma la password lanza una PasswordException
        user.setPassword(newPassword);
        UserManager manager = UserManagerFactory.create(user);
        value &= manager.update();
        return value;
    }
}

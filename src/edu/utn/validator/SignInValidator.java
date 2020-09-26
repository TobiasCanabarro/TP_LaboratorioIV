package edu.utn.validator;

import edu.utn.dao.UserDao;
import edu.utn.entity.User;
import edu.utn.manager.UserManager;

public class SignInValidator {

    //

    public boolean existsUser (UserManager manager, User user) {
        User found = manager.get(user.getEmail()); //key para buscar el registro. Esta hardcodeado el email para la busqueda
        if (found != null) {
            return found.getEmail().equals(user.getEmail());
        }
        return false;
    }

}

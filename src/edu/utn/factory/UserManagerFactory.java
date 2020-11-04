package edu.utn.factory;

import edu.utn.manager.UserManager;
import edu.utn.mapper.UserMapper;
import edu.utn.validator.UserValidator;


public class UserManagerFactory implements Factory {

    public static UserManager create () {
        return new UserManager(new UserMapper(), new UserValidator());
    }
}

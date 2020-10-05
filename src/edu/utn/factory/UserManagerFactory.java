package edu.utn.factory;

import edu.utn.entity.User;
import edu.utn.manager.UserManager;
import edu.utn.mapper.UserMapper;

public class UserManagerFactory {

    public static UserManager create (User user) {
        return new UserManager(new UserMapper(user));
    }
}

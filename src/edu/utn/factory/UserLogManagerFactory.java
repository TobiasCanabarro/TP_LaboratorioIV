package edu.utn.factory;

import edu.utn.entity.UserLog;
import edu.utn.manager.UserLogManager;
import edu.utn.mapper.UserLogMapper;


public class UserLogManagerFactory implements Factory{

    public static UserLogManager create (UserLog user) {
        return new UserLogManager(new UserLogMapper(user));
    }
}

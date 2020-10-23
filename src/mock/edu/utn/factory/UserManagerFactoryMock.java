package mock.edu.utn.factory;

import edu.utn.entity.User;
import edu.utn.factory.Factory;
import mock.edu.utn.manager.UserManagerMock;
import mock.edu.utn.mapper.UserMapperMock;

public class UserManagerFactoryMock implements Factory {

    public static UserManagerMock create (User user){
        return new UserManagerMock(new UserMapperMock(user));
    }

}

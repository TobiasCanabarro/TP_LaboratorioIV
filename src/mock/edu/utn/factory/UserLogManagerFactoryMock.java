package mock.edu.utn.factory;

import edu.utn.entity.UserLog;
import edu.utn.factory.Factory;
import mock.edu.utn.manager.UserLogManagerMock;
import mock.edu.utn.mapper.UserLogMapperMock;

public class UserLogManagerFactoryMock implements Factory {

     public static UserLogManagerMock create (UserLog user) {
        return new UserLogManagerMock(new UserLogMapperMock(user));
     }

}

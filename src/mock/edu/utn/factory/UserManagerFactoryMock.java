package mock.edu.utn.factory;

import edu.utn.entity.User;
import edu.utn.factory.Factory;
import mock.edu.utn.manager.UserManagerMock;
import mock.edu.utn.mapper.UserMapperMock;
import mock.edu.utn.validator.UserValidatorMock;

public class UserManagerFactoryMock implements Factory {

    public static UserManagerMock create  (){
        return new UserManagerMock(new UserMapperMock(), new UserValidatorMock());
    }

}

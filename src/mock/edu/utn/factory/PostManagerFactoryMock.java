package mock.edu.utn.factory;

import edu.utn.factory.Factory;
import mock.edu.utn.manager.PostManagerMock;
import mock.edu.utn.mapper.PostMapperMock;
import mock.edu.utn.validator.PostValidatorMock;

public class PostManagerFactoryMock implements Factory {

    public static PostManagerMock create (){
        return new PostManagerMock(new PostMapperMock(), new PostValidatorMock());
    }
}

package mock.edu.utn.factory;

import edu.utn.factory.Factory;
import mock.edu.utn.manager.RequestRelationshipManagerMock;
import mock.edu.utn.mapper.RequestRelationshipMapperMock;
import mock.edu.utn.validator.RequestRelationshipValidatorMock;

public class RequestRelationshipManagerFactoryMock implements Factory {

    public static RequestRelationshipManagerMock create (){
        return new RequestRelationshipManagerMock(new RequestRelationshipMapperMock(), new RequestRelationshipValidatorMock());
    }
}

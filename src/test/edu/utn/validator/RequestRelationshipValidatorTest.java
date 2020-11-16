package test.edu.utn.validator;

import edu.utn.entity.RequestRelationship;
import edu.utn.validator.RequestRelationshipValidator;
import mock.edu.utn.factory.RequestRelationshipManagerFactoryMock;
import mock.edu.utn.manager.RequestRelationshipManagerMock;
import mock.edu.utn.validator.RequestRelationshipValidatorMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestRelationshipValidatorTest {

    @Test
    void existRelation() {

        long idUserReceive = 1;
        long idUserSend = 2;

        RequestRelationshipManagerMock managerMock = RequestRelationshipManagerFactoryMock.create();
        managerMock.getValidator().setValid(true);
        RequestRelationship relationship = managerMock.get(idUserReceive, idUserSend);
        boolean value = relationship != null;

        assertEquals(true, value);
    }

    @Test
    void existRelationFail() {

        long idUserReceive = 1;
        long idUserSend = 2;

        RequestRelationshipManagerMock managerMock = RequestRelationshipManagerFactoryMock.create();
        managerMock.getValidator().setValid(false);
        RequestRelationship relationship = managerMock.get(idUserReceive, idUserSend);
        boolean value = relationship != null;

        assertEquals(false, value);
    }
}
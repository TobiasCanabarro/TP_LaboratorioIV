package test.edu.utn.manager;

import edu.utn.entity.RequestRelationship;
import mock.edu.utn.factory.RequestRelationshipManagerFactoryMock;
import mock.edu.utn.manager.RequestRelationshipManagerMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestRelationshipManagerTest {

    @Test
    void sendRequestOk (){
        RequestRelationship request = new RequestRelationship(1, 2);
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        boolean value = manager.save(request);
        assertEquals(true, value);
    }

    @Test
    void sendRequestFail (){
        RequestRelationship request = new RequestRelationship(1, 2);
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        boolean value = manager.save(request);
        assertEquals(false, value);
    }

    @Test
    void acceptRequestOk (){
        RequestRelationship request = new RequestRelationship(1, 2);
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        boolean value = manager.update(request);
        assertEquals(true, value);
    }

    @Test
    void acceptRequestFail (){
        RequestRelationship request = new RequestRelationship(1, 2);
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        boolean value = manager.update(request);
        assertEquals(false, value);
    }

    @Test
    void refuseRequestOk(){
        assertEquals(true, true);
    }

    @Test
    void refuseRequestFail(){

    }

    @Test
    void searchRelationshipOk (){
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        RequestRelationship request = manager.get(1, 2);
        boolean value = request != null;
        assertEquals(true, value);
    }

    @Test
    void searchRelationshipFail (){
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        RequestRelationship request = manager.get(1, 2);
        boolean value = request != null;
        assertEquals(false, value);
    }


}

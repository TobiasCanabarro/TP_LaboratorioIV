package test.edu.utn.manager;

import edu.utn.entity.RequestRelationship;
import edu.utn.entity.User;
import mock.edu.utn.factory.RequestRelationshipManagerFactoryMock;
import mock.edu.utn.manager.RequestRelationshipManagerMock;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        RequestRelationship request = new RequestRelationship(1, 2);
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        boolean value = manager.delete(request);
        assertEquals(true, value);
    }

    @Test
    void refuseRequestFail(){
        RequestRelationship request = new RequestRelationship(1, 2);
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        boolean value = manager.delete(request);
        assertEquals(false, value);
    }

    @Test
    void deleteRelationshipOk () {
        RequestRelationship request = new RequestRelationship(1, 2);
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        boolean value = manager.delete(request);
        assertEquals(true, value);
    }

    @Test
    void deleteRelationshipFail () {
        RequestRelationship request = new RequestRelationship(1, 2);
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        boolean value = manager.delete(request);
        assertEquals(false, value);
    }

    @Test
    void myFriendsOk (){
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        List<User> friends = manager.myFriends(14);
        boolean value = friends != null;
        assertEquals(true, value);
    }

    @Test
    void myFriendsFail (){
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        List<User> friends = manager.myFriends(14);
        boolean value = friends != null;
        assertEquals(false, value);
    }

    @Test
    void saveOk (){
        RequestRelationship request = new RequestRelationship(1, 2);
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        boolean value = manager.save(request);
        assertEquals(true, value);
    }

    @Test
    void saveFail(){
        RequestRelationship request = new RequestRelationship(1, 2);
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        boolean value = manager.save(request);
        assertEquals(false, value);
    }

    @Test
    void updateOk(){
        RequestRelationship request = new RequestRelationship(1, 2);
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        boolean value = manager.update(request);
        assertEquals(true, value);
    }

    @Test
    void updateFail(){
        RequestRelationship request = new RequestRelationship(1, 2);
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        boolean value = manager.update(request);
        assertEquals(false, value);
    }

    @Test
    void getForIdOk(){
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        RequestRelationship relation = manager.get(14);
        boolean value = relation != null;
        assertEquals(true, value);
    }

    @Test
    void getForIdFail (){
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        RequestRelationship relation = manager.get(14);
        boolean value = relation != null;
        assertEquals(false, value);
    }

    @Test
    void getForIdReceiveAndIdSendOk (){
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        RequestRelationship relation = manager.get(14, 15);
        boolean value = relation != null;
        assertEquals(true, value);
    }

    @Test
    void getForIdReceiveAndIdSendFail (){
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        RequestRelationship relation = manager.get(14, 15);
        boolean value = relation != null;
        assertEquals(false, value);
    }


    @Test
    void getAllOk(){
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        List<RequestRelationship> relation = manager.getAllRequest(14);
        boolean value = relation != null;
        assertEquals(true, value);
    }

    @Test
    void getAllFail(){
        RequestRelationshipManagerMock manager = RequestRelationshipManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        List<RequestRelationship> relations = manager.getAllRequest(14);
        boolean value = relations != null;
        assertEquals(false, value);
    }

}

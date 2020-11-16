package test.edu.utn.mapper;

import edu.utn.entity.RequestRelationship;
import edu.utn.mapper.RequestRelationshipMapper;
import mock.edu.utn.mapper.RequestRelationshipMapperMock;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RequestRelationshipMapperTest {

    private static final RequestRelationship REQUEST_RELATIONSHIP = new RequestRelationship(1, 2);

    @Test
    void save() {

        RequestRelationshipMapperMock mapperMock = new RequestRelationshipMapperMock();
        mapperMock.setValid(true);
        boolean value = mapperMock.save(REQUEST_RELATIONSHIP);

        assertEquals(true, value);
    }

    @Test
    void saveFail() {

        RequestRelationshipMapperMock mapperMock = new RequestRelationshipMapperMock();
        mapperMock.setValid(false);
        boolean value = mapperMock.save(REQUEST_RELATIONSHIP);

        assertEquals(false, value);
    }

    @Test
    void update() {

        RequestRelationshipMapperMock mapperMock = new RequestRelationshipMapperMock();
        mapperMock.setValid(true);
        boolean value = mapperMock.update(REQUEST_RELATIONSHIP);

        assertEquals(true, value);
    }

    @Test
    void updateFail() {
        RequestRelationshipMapperMock mapperMock = new RequestRelationshipMapperMock();
        mapperMock.setValid(false);
        boolean value = mapperMock.update(REQUEST_RELATIONSHIP);

        assertEquals(false, value);
    }

    @Test
    void delete() {

        RequestRelationshipMapperMock mapperMock = new RequestRelationshipMapperMock();
        mapperMock.setValid(true);
        boolean value = mapperMock.delete(REQUEST_RELATIONSHIP);

        assertEquals(true, value);
    }

    @Test
    void deleteFail() {

        RequestRelationshipMapperMock mapperMock = new RequestRelationshipMapperMock();
        mapperMock.setValid(false);
        boolean value = mapperMock.delete(REQUEST_RELATIONSHIP);

        assertEquals(false, value);
    }

    @Test
    void get() {

        RequestRelationshipMapperMock mapperMock = new RequestRelationshipMapperMock();
        mapperMock.setValid(true);
        RequestRelationship relationship = mapperMock.get(REQUEST_RELATIONSHIP.getIdUserReceive(), REQUEST_RELATIONSHIP.getIdUserSend());
        boolean value = relationship != null;

        assertEquals(true, value);

    }

    @Test
    void getFail() {

        RequestRelationshipMapperMock mapperMock = new RequestRelationshipMapperMock();
        mapperMock.setValid(false);
        RequestRelationship relationship = mapperMock.get(REQUEST_RELATIONSHIP.getIdUserReceive(), REQUEST_RELATIONSHIP.getIdUserSend());
        boolean value = relationship != null;

        assertEquals(false, value);
    }

    @Test
    void getAll() {

        RequestRelationshipMapperMock mapperMock = new RequestRelationshipMapperMock();
        mapperMock.setValid(true);
        List<Map<String ,Object>> relationship = mapperMock.getAll(1);
        boolean value = relationship != null;

        assertEquals(true, value);
    }

    @Test
    void getAllFail() {

        RequestRelationshipMapperMock mapperMock = new RequestRelationshipMapperMock();
        mapperMock.setValid(false);
        List<Map<String ,Object>> relationship = mapperMock.getAll(1);
        boolean value = relationship != null;

        assertEquals(false, value);
    }

}
package mock.edu.utn.manager;

import edu.utn.entity.RequestRelationship;
import edu.utn.entity.User;
import edu.utn.manager.Manager;
import mock.edu.utn.factory.UserManagerFactoryMock;
import mock.edu.utn.mapper.RequestRelationshipMapperMock;
import mock.edu.utn.validator.RequestRelationshipValidatorMock;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestRelationshipManagerMock implements Manager <RequestRelationship> {

    private RequestRelationshipMapperMock mapper;
    private RequestRelationshipValidatorMock validator;

    public RequestRelationshipManagerMock(RequestRelationshipMapperMock mapper, RequestRelationshipValidatorMock validator) {
        setMapper(mapper);
        setValidator(validator);
    }

    @Override
    public boolean save(RequestRelationship request) {
        boolean value = validator.isValid();
        getMapper().setValid(value);
        if(value){
            value = getMapper().save(request);
        }
        return value;
    }

    @Override
    public boolean update(RequestRelationship request) {
        boolean value = validator.isValid(request);
        getMapper().setValid(value);
        if(value){
            value = getMapper().update(request);
        }
        return value;
    }

    @Override
    public boolean delete(RequestRelationship request) {
        boolean value = validator.isValid(request);
        getMapper().setValid(value);
        if(value){
            value = getMapper().delete(request);
        }
        return value;
    }


    @Override
    public RequestRelationship get(long id) {
        RequestRelationship request = null;
        boolean value = validator.isValid();
        getMapper().setValid(value);
        if(value){
            request = mapper.get(id);
        }
        return request;
    }

    public RequestRelationship get (long idReceive, long idSend){
        RequestRelationship request = null;
        boolean value = validator.isValid();
        getMapper().setValid(value);
        if(value){
            request = mapper.get(idReceive, idSend);
        }
        return request;
    }


    public List<RequestRelationship> getAllRequest (long id) {
        List<RequestRelationship> relations = null;
        boolean value = validator.isValid();
        getMapper().setValid(value);
        if(value){
             relations = mapper.getAllRequest(id);
        }
        return relations;
    }

    public List<Map<String, Object>> getAll (long id) {
        List<Map<String ,Object>> relation = null;
        boolean value = validator.isValid();
        getMapper().setValid(value);
        if(value){
            relation = mapper.getAll(id);
        }
        return relation;
    }

    public List<User> myFriends (long id){
        List<User> friends = null;
        if(validator.isValid()){
            friends = new ArrayList<>();
            friends.add(new User("Tobias", "tobias123", "Canabarro",
                    "tobias@gmail.com", "Tobi", new Date(9999)));
        }

        return friends;
    }


    public RequestRelationshipMapperMock getMapper() {
        return mapper;
    }

    public void setMapper(RequestRelationshipMapperMock mapper) {
        this.mapper = mapper;
    }

    public RequestRelationshipValidatorMock getValidator() {
        return validator;
    }

    public void setValidator(RequestRelationshipValidatorMock validator) {
        this.validator = validator;
    }
}

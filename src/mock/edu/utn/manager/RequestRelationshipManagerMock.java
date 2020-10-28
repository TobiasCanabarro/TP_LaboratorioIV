package mock.edu.utn.manager;

import edu.utn.entity.RequestRelationship;
import edu.utn.manager.Manager;
import mock.edu.utn.mapper.RequestRelationshipMapperMock;
import mock.edu.utn.validator.RequestRelationshipValidatorMock;

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

    public RequestRelationship get (long idReceive, long idSend){
        RequestRelationship request = null;
        boolean value = validator.isValid();
        getMapper().setValid(value);
        if(value){
            request = mapper.get(idReceive, idSend);
        }
        return request;
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

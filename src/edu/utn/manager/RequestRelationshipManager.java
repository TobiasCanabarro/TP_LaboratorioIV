package edu.utn.manager;

import edu.utn.entity.RequestRelationship;
import edu.utn.entity.User;
import edu.utn.factory.UserManagerFactory;
import edu.utn.mapper.RequestRelationshipMapper;
import edu.utn.validator.RequestRelationshipValidator;

public class RequestRelationshipManager {

    private RequestRelationshipValidator validator;
    private RequestRelationshipMapper mapper;

    public RequestRelationshipManager(RequestRelationshipMapper mapper) {
        setMapper(mapper);
    }

    public boolean sendRequest (String sendEmail, String requestEmail) {
        UserManager manager = UserManagerFactory.create();
        User userSend = manager.get(sendEmail);
        User userRequest = manager.get(requestEmail);
        if(userRequest == null && userSend == null){
            return false;
        }
        RequestRelationship requestRelationship = new RequestRelationship(userSend.getId(), userRequest.getId());
        return  save(requestRelationship);
    }

    public boolean save (RequestRelationship requestRelationship) {
        boolean value = true;//TODO validator
        if(value){
            value &= ma
        }
    }

    public RequestRelationshipValidator getValidator() {
        return validator;
    }

    public void setValidator(RequestRelationshipValidator validator) {
        this.validator = validator;
    }

    public RequestRelationshipMapper getMapper() {
        return mapper;
    }

    public void setMapper(RequestRelationshipMapper mapper) {
        this.mapper = mapper;
    }
}

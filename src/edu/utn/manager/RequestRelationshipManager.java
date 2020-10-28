package edu.utn.manager;

import edu.utn.entity.RequestRelationship;
import edu.utn.entity.User;
import edu.utn.factory.UserManagerFactory;
import edu.utn.mapper.RequestRelationshipMapper;
import edu.utn.validator.RequestRelationshipValidator;

public class RequestRelationshipManager implements Manager <RequestRelationship> {

    private RequestRelationshipValidator validator;
    private RequestRelationshipMapper mapper;

    public RequestRelationshipManager(RequestRelationshipMapper mapper, RequestRelationshipValidator validator) {
        setMapper(mapper);
        setValidator(validator);
    }

    @Override
    public boolean save(RequestRelationship requestRelationship) {
        return mapper.save(requestRelationship);
    }

    @Override
    public boolean update(RequestRelationship requestRelationship) {
        return mapper.update(requestRelationship);
    }

    public RequestRelationship get(long idReceive, long idSend) {
        return mapper.get(idReceive, idSend);
    }

    public boolean sendRequest (String receiveEmail, String  sendEmail) {
       RequestRelationship requestRelationship = searchRelationship(receiveEmail, sendEmail);
        if(validator.isNull(requestRelationship)){
            return false;
        }
        return  save(requestRelationship);
    }

    public boolean acceptRequest (String receiveEmail, String sendEmail){
        RequestRelationship requestRelationship = searchRelationship(receiveEmail, sendEmail);
        if(validator.isNull(requestRelationship)){
            return false;
        }
        requestRelationship.setState(true);
        return update(requestRelationship);
    }

    //rechaza la solicitud de amistad
    public boolean refuseRequest (){
        //TODO tendria que hacer un delete fisico en la tabla request_relationship
        return false;
    }

    private RequestRelationship searchRelationship (String receiveEmail, String sendEmail){
        UserManager manager = UserManagerFactory.create();
        User userReceive = manager.get(receiveEmail);
        User userSend = manager.get(sendEmail);
        if(manager.getValidator().isNull(userReceive) || manager.getValidator().isNull(userSend)){
            return null;
        }
        return validator.existRelation(userReceive.getId(), userSend.getId());////new RequestRelationship(userReceive.getId(), userSend.getId());
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

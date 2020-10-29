package edu.utn.manager;

import edu.utn.entity.RequestRelationship;
import edu.utn.entity.User;
import edu.utn.factory.RequestRelationshipManagerFactory;
import edu.utn.factory.UserManagerFactory;
import edu.utn.mapper.RequestRelationshipMapper;
import edu.utn.validator.RequestRelationshipValidator;

import java.util.List;

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

    @Override
    public boolean delete(RequestRelationship requestRelationship) {
        return mapper.delete(requestRelationship);
    }

    public RequestRelationship get(long idRequest) {
        return mapper.get(idRequest);
    }

    public RequestRelationship get (long idUserReceive, long idUserSend){
        return mapper.get(idUserReceive, idUserSend);
    }

    public List<RequestRelationship> getAll (long id){
        return mapper.getAll(id);
    }

    public boolean sendRequest (String receiveEmail, String  sendEmail) {
       UserManager manager = UserManagerFactory.create();
       User userReceive = manager.get(receiveEmail);
       User userSend = manager.get(sendEmail);
       boolean value = false;
       RequestRelationship requestRelationship = validator.existRelation(userReceive.getId(), userSend.getId());
       if(validator.isNull(requestRelationship)){
           requestRelationship = new RequestRelationship(userReceive.getId(), userSend.getId());
           value = save(requestRelationship);
       }
       return value;
    }

    //long idRequest
    public boolean acceptRequest (long idRequest){
        RequestRelationship requestRelationship = get(idRequest);
        if(validator.isNull(requestRelationship)){
            return false;
        }
        requestRelationship.setState(true);
        return update(requestRelationship);
    }

    public boolean refuseRequest (long idRequest){
        RequestRelationship requestRelationship = get(idRequest);
        if(validator.isNull(requestRelationship)){
            return false;
        }
        return delete(requestRelationship);
    }

    public boolean deleteRelationship (long idRequest){
        return refuseRequest(idRequest);
    }

    public List<User> getAllRelationship (String email) {
        UserManager manager = UserManagerFactory.create();
        User user = manager.get(email);

        return null;
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

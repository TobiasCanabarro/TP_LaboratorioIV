package edu.utn.manager;

import edu.utn.entity.RequestRelationship;
import edu.utn.entity.User;
import edu.utn.factory.UserManagerFactory;
import edu.utn.enums.Result;
import edu.utn.log.LogHelper;
import edu.utn.mapper.RequestRelationshipMapper;
import edu.utn.validator.RequestRelationshipValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public RequestRelationship get(long idRequest) {
        return mapper.get(idRequest);
    }

    public RequestRelationship get (long idUserReceive, long idUserSend){
        return mapper.get(idUserReceive, idUserSend);
    }

    public List<Map<String, Object>> getAll (long id){
        return mapper.getAll(id);
    }

    public List<RequestRelationship> getAllRequest (long id) {
        return mapper.getAllRequest(id);
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
       if(value){
           LogHelper.createNewDebugLog(Result.SEND_REQUEST_OK);
       }
       return value;
    }

    public boolean acceptRequest (long idRequest){
        RequestRelationship requestRelationship = get(idRequest);
        if(validator.isNull(requestRelationship)){
            return false;
        }
        requestRelationship.setState(true);
        LogHelper.createNewDebugLog(Result.ACCEPT_REQUEST_OK);
        return update(requestRelationship);
    }

    public boolean refuseRequest (long idRequest){
        RequestRelationship requestRelationship = get(idRequest);
        if(validator.isNull(requestRelationship)){
            return false;
        }
        LogHelper.createNewDebugLog(Result.REFUSE_REQUEST_OK);
        return delete(requestRelationship);
    }

    public boolean deleteRelationship (long idRequest){
        return refuseRequest(idRequest);
    }

    public List<User> myFriends (long id) {
        UserManager manager = UserManagerFactory.create();
        List<Map<String, Object>> relations = getAll(id);
        List<User> friends = new ArrayList<>();
        long idUserReceive;
        long idUserSend ;

        for(Map<String, Object> relation : relations){
            idUserReceive = (long)relation.get("id_user_receive");
            idUserSend = (long)(relation.get("id_user_send"));

            if(idUserReceive != id){
                friends.add(manager.get(idUserReceive));
            }
            else {
                friends.add(manager.get(idUserSend));
            }
        }
        return friends;
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

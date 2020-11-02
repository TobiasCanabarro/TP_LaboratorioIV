package mock.edu.utn.mapper;

import edu.utn.entity.RequestRelationship;
import edu.utn.factory.RequestRelationshipManagerFactory;
import edu.utn.manager.RequestRelationshipManager;
import edu.utn.mapper.Mapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestRelationshipMapperMock implements Mapper<RequestRelationship> {

    private boolean valid;

    @Override
    public boolean save(RequestRelationship request) {
        return isValid();
    }

    @Override
    public boolean update(RequestRelationship request) {
        return isValid();
    }

    @Override
    public boolean delete(RequestRelationship requestRelationship) {
        return isValid();
    }

    public RequestRelationship get (long idReceive, long idSend){
        return isValid()?new RequestRelationship(1, idReceive, idSend, isValid()):null;
    }

    public RequestRelationship get (long id){
        return isValid()?new RequestRelationship(1, 14, 15, isValid()):null;
    }

    public List<Map<String ,Object>> getAll (long id){
        List<Map<String ,Object>> relations = null;
        if(isValid()){
            RequestRelationship relation = new RequestRelationship(1, 14, 15, true);
            relations = new ArrayList<>();
        }
        return relations;
    }

    public List<RequestRelationship> getAllRequest (long id) {
        List<RequestRelationship> relationships = null;
        if(isValid()){
            relationships = new ArrayList<>();
            relationships.add(new RequestRelationship(1, id, 15, isValid()));
        }
        return relationships;
    }

    @Override
    public Map<Integer, Object> createParameters(RequestRelationship requestRelationship) {
        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
        return manager.getMapper().createParameters(requestRelationship);
    }

    public Map<Integer, Object> createParametersForUpdate (RequestRelationship requestRelationship) {
        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
        return manager.getMapper().createParametersForUpdate(requestRelationship);
    }

    @Override
    public RequestRelationship getEntityRecord(Map<String, Object> record) {
        return new RequestRelationship((long)record.get("id_request"), (long)record.get("id_user_receive"),
                (long)record.get("id_user_send"), (boolean)record.get("state"));
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}

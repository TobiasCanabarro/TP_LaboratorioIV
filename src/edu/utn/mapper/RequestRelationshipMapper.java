package edu.utn.mapper;

import edu.utn.dao.RequestRelationshipDao;
import edu.utn.entity.RequestRelationship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestRelationshipMapper implements Mapper <RequestRelationship> {

    @Override
    public boolean save(RequestRelationship requestRelationship)  {
        Map<Integer, Object> parameters = createParameters(requestRelationship);
        RequestRelationshipDao relationshipDao = RequestRelationshipDao.getRequestRelationshipDao();
        int id = relationshipDao.save(parameters);
        return id != 0;
    }

    @Override
    public boolean update(RequestRelationship requestRelationship) {
        Map<Integer, Object> parameters = createParametersForUpdate(requestRelationship);
        RequestRelationshipDao requestDao = RequestRelationshipDao.getRequestRelationshipDao();
        int id = requestDao.update(parameters);
        return id != 0;
    }

    @Override
    public boolean delete(RequestRelationship requestRelationship) {
        Map<Integer, Object> parameters = createParameters(requestRelationship);
        RequestRelationshipDao requestDao = RequestRelationshipDao.getRequestRelationshipDao();
        int id = requestDao.delete(parameters);
        return id != 0;
    }

    public RequestRelationship get (long idReceive, long idSend){
        RequestRelationshipDao requestDao = RequestRelationshipDao.getRequestRelationshipDao();
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(1, idReceive);
        parameters.put(2, idSend);
        RequestRelationship request = null;
        List<Map<String, Object>> records = requestDao.getOtherParam(parameters);

        if(records.size() > 0){
            Map<String, Object> record = records.get(0);
            request = getRelationOnRecord(record);
        }
        return request;
    }

    public RequestRelationship get (long id){
        RequestRelationshipDao requestDao = RequestRelationshipDao.getRequestRelationshipDao();
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(1, id);
        RequestRelationship relationship = null;
        List<Map<String, Object>> records = requestDao.get(parameters);

        if(records.size() > 0){
            Map<String, Object> record = records.get(0);
            relationship = getRelationOnRecord(record);
        }
        return relationship;
    }

    public List<RequestRelationship> getAll (long id){
        RequestRelationshipDao requestDao = RequestRelationshipDao.getRequestRelationshipDao();
        List<RequestRelationship> relations = new ArrayList<>();
        Map<Integer, Object> parameters = new HashMap<>();

        parameters.put(1, id);
        List<Map<String, Object>> records = requestDao.getAll(parameters);

        for(Map<String, Object> record : records){
            relations.add( getRelationOnRecord(record) );
        }
        return relations;
    }

    @Override
    public Map<Integer, Object> createParameters(RequestRelationship requestRelationship) {
        int i = 1;
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(i++, requestRelationship.getIdUserReceive());
        parameters.put(i++, requestRelationship.getIdUserSend());
        return parameters;
    }

    public Map<Integer, Object> createParametersForUpdate (RequestRelationship requestRelationship){
        Map<Integer, Object> parameters = createParameters(requestRelationship);
        int size = parameters.size();;
        parameters.put(++size, requestRelationship.isState());
        parameters.put(++size, requestRelationship.getIdUserReceive());
        parameters.put(++size, requestRelationship.getIdUserSend());
        return parameters;
    }

    private RequestRelationship getRelationOnRecord(Map<String, Object> record){
        return new RequestRelationship((long)record.get("id_request"), (long)record.get("id_user_receive"),
                (long)record.get("id_user_send"), (boolean)record.get("state"));
    }

}

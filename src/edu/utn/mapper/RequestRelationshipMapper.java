package edu.utn.mapper;

import edu.utn.dao.RequestRelationshipDao;
import edu.utn.entity.RequestRelationship;

import java.util.HashMap;
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
        return false;
    }

    public RequestRelationship get (long id){
        return null;
    }

    @Override
    public Map<Integer, Object> createParameters(RequestRelationship requestRelationship) {
        int i = 1;
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(i++, requestRelationship.getIdUserReceive());
        parameters.put(i++, requestRelationship.getIdUserSend());
        return parameters;
    }
}

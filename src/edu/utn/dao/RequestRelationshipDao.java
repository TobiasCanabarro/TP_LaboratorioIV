package edu.utn.dao;

import java.util.List;
import java.util.Map;

public class RequestRelationshipDao extends DataAccess{

    private static final String INSERT_REQUEST_RELATIONSHIP = "INSERT INTO lab.request_relationship (id_request, id_user_receive, id_user_send, state) values (?, ?, ?, ?)";
    private static final String SELECT_REQUEST_RELATIONSHIP =  "SELECT * FROM lab.request_relationship WHERE id_user_send = ?";
    private static final String UPDATE_REQUEST_RELATIONSHIP = "UPDATE lab.request_relationship set id_request = ?, id_user_receive = ?, id_user_send = ?, state = ?";

    private static RequestRelationshipDao requestRelationshipDao;

    protected RequestRelationshipDao (){super();}

    public static RequestRelationshipDao getRequestRelationshipDao(){
        if(requestRelationshipDao == null){
            requestRelationshipDao = new RequestRelationshipDao();
        }
        return requestRelationshipDao;
    }

    public int save (Map<Integer, Object> parameters){
        return write(INSERT_REQUEST_RELATIONSHIP, parameters);
    }

    public List<Map<String, Object>> get (Map<Integer, Object> parameters){
        return read (SELECT_REQUEST_RELATIONSHIP, parameters);
    }

    public int update(Map<Integer, Object> parameters){
        return write(UPDATE_REQUEST_RELATIONSHIP, parameters);
    }

}

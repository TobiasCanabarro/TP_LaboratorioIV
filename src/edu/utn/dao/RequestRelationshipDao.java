package edu.utn.dao;

import java.util.List;
import java.util.Map;

public class RequestRelationshipDao extends DataAccess implements Dao{

    private static final String INSERT_REQUEST_RELATIONSHIP = "INSERT INTO lab.request_relationship (id_user_receive, id_user_send) values (?, ?)";
    private static final String SELECT_REQUEST_RELATIONSHIP =  "SELECT * FROM lab.request_relationship WHERE id_user_receive = ? AND id_user_send = ?";
    private static final String UPDATE_REQUEST_RELATIONSHIP = "UPDATE lab.request_relationship set id_user_receive = ?, id_user_send = ?, state = ?";
    private static final String DELETE_REQUEST_RELATIONSHIP = "DELETE FROM lab.request_relationship WHERE id_user_receive = ? AND id_user_send = ?";

    private static RequestRelationshipDao requestRelationshipDao;

    protected RequestRelationshipDao (){super();}

    public static RequestRelationshipDao getRequestRelationshipDao(){
        if(requestRelationshipDao == null){
            requestRelationshipDao = new RequestRelationshipDao();
        }
        return requestRelationshipDao;
    }

    @Override
    public int save (Map<Integer, Object> parameters){
        return write(INSERT_REQUEST_RELATIONSHIP, parameters);
    }

    @Override
    public List<Map<String, Object>> get (Map<Integer, Object> parameters){
        return read (SELECT_REQUEST_RELATIONSHIP, parameters);
    }

    @Override
    public int update(Map<Integer, Object> parameters){
        return write(UPDATE_REQUEST_RELATIONSHIP, parameters);
    }

    //El borrado en este caso se hace de manera fisica
    @Override
    public int delete(Map<Integer, Object> parameters) {
        return write(DELETE_REQUEST_RELATIONSHIP, parameters);
    }

}

package edu.utn.dao;

import java.util.List;
import java.util.Map;

public class RequestRelationshipDao extends DataAccess implements Dao{

    private static final String INSERT_REQUEST_RELATIONSHIP = "INSERT INTO lab.request_relationship (id_user_receive, id_user_send) values (?, ?)";

    private static final String SELECT_REQUEST_RELATIONSHIP =  "SELECT * FROM lab.request_relationship WHERE id_request = ?";

    private static final String SELECT_REQUEST_RELATIONSHIP_OTHER =  "select * from lab.request_relationship" +
            " where id_user_receive = ? AND id_user_send = ?" +
            " OR id_user_receive = ? AND id_user_send = ?";


    private static final String UPDATE_REQUEST_RELATIONSHIP = "UPDATE lab.request_relationship set id_user_receive = ?, id_user_send = ?, state = ?" +
            " WHERE id_user_receive = ? AND id_user_send = ?";

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

    //Trae a los amigos del usuario
    public List<Map<String, Object>> getAll (long id){

        String query = "select id_user_receive, id_user_send from lab.request_relationship " +
                " where state = true AND id_user_receive = " + id + " " + "or state = true AND id_user_send = " + id;
        return read (query);
    }

    //Trae las solicitudes pendientes del usuario
    public List<Map<String, Object>> getAllRequest (long id){

        String query = "SELECT * FROM lab.request_relationship AS r " +
                "WHERE r.id_user_receive = " + id + " " + "AND r.state = false";
        return read(query);
    }

    //Trae una relacion de la tabla
    public List<Map<String, Object>> getOtherParam (Map<Integer, Object> parameters){
        return read (SELECT_REQUEST_RELATIONSHIP_OTHER, parameters);
    }

    //Hace un update sobre la tabla request_relationship
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

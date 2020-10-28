package edu.utn.validator;

import edu.utn.entity.RequestRelationship;
import edu.utn.factory.RequestRelationshipManagerFactory;
import edu.utn.manager.RequestRelationshipManager;


public class RequestRelationshipValidator extends Validator <RequestRelationship>{

    public RequestRelationship existRelation (long idReceive, long idSend){
        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
        RequestRelationship relation = manager.get(idReceive, idSend);
        return relation;

    }

}

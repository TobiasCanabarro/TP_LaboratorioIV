package edu.utn.validator;

import edu.utn.entity.RequestRelationship;
import edu.utn.factory.RequestRelationshipManagerFactory;
import edu.utn.manager.RequestRelationshipManager;

public class RequestRelationshipValidator extends Validator <RequestRelationship>{

    public RequestRelationship existRelation (long idUserReceive, long idUserSend){
        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
        return  manager.get(idUserReceive, idUserSend);

    }

}

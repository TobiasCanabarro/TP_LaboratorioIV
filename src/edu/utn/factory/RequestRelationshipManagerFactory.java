package edu.utn.factory;

import edu.utn.manager.RequestRelationshipManager;
import edu.utn.mapper.RequestRelationshipMapper;
import edu.utn.validator.RequestRelationshipValidator;

public class RequestRelationshipManagerFactory implements Factory{

    public static RequestRelationshipManager create () {
        return new RequestRelationshipManager(new RequestRelationshipMapper(), new RequestRelationshipValidator());
    }

}

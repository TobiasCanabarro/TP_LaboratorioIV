package mock.edu.utn.mapper;

import edu.utn.entity.RequestRelationship;
import edu.utn.mapper.Mapper;

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

    public RequestRelationship get (long idReceive, long idSend){
        return isValid()?new RequestRelationship(1, idReceive, idSend, isValid()):null;
    }

    //Este metodo en esta clase no se usa :D
    @Override
    public Map<Integer, Object> createParameters(RequestRelationship request) {
        return null;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}

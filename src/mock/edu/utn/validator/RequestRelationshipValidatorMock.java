package mock.edu.utn.validator;

import edu.utn.entity.RequestRelationship;

public class RequestRelationshipValidatorMock  {

    private boolean valid;

    public boolean isValid (RequestRelationship relationship){
        return isValid();
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}

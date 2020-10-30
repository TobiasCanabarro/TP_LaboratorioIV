package mock.edu.utn.validator;

import edu.utn.entity.User;

public class UserValidatorMock {

    private boolean valid;

    public boolean isValid (User user){
        return isValid();
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}

package mock.edu.utn.validator;

import edu.utn.entity.UserPost;

public class PostValidatorMock {

    private boolean valid;

    public boolean isValid (UserPost post){
        return isValid();
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}

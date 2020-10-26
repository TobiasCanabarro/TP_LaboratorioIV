package mock.edu.utn.validator;

public class UserValidatorMock {

    private boolean valid;

    public boolean isValid (String email){
        return isValid();
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}

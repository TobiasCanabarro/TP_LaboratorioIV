package edu.utn.validator;

import edu.utn.entity.UserLog;
import edu.utn.exception.EmailException;

public class UserLogValidator extends Validator{

    public void isValid (UserLog userLog) throws EmailException {
        isValidEmail(userLog.getEmail());
    }

}

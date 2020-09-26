package edu.utn.validator;

import edu.utn.entity.User;
import edu.utn.exception.EmailException;
import edu.utn.exception.SurNameException;
import edu.utn.exception.NameException;

public class UserValidator extends Validator{

    private static final String NAME_EXCEPTION = "NAME EXCEPTION";
    private static final String SURNAME_EXCEPTION = "SURNAME EXCEPTION";

    public void isValid (User user) throws NameException, SurNameException, EmailException {
        isValidName(user);
        isValidSurname(user);
        isValidEmail(user.getEmail());
    }

    private void isValidName (User user) throws NameException {
        boolean value = user.getName() != null && !user.getName().isEmpty();
        value &= insideAlphabet(user.getName());
        if (!value) {
            throw new NameException(NAME_EXCEPTION);
        }
    }

    private void isValidSurname (User user) throws SurNameException {
        boolean value = user.getSurname() != null && !user.getSurname().isEmpty();
        value &= insideAlphabet(user.getName());
        if (!value) {
            throw new SurNameException(SURNAME_EXCEPTION);
        }
    }

}

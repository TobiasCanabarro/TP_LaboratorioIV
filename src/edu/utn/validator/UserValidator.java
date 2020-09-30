package edu.utn.validator;

import edu.utn.dao.UserLogDao;
import edu.utn.dto.UserLogDto;
import edu.utn.entity.User;
import edu.utn.entity.UserLog;
import edu.utn.exception.EmailException;
import edu.utn.exception.SurNameException;
import edu.utn.exception.NameException;
import edu.utn.manager.UserLogManager;
import edu.utn.mapper.UserLogMapper;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

public class UserValidator extends Validator {

    private static final String NAME_EXCEPTION = "NAME EXCEPTION";
    private static final String SURNAME_EXCEPTION = "SURNAME EXCEPTION";
    private static final int MAX_ATTEMPT = 3;

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

    //TODO Puede que lo tenga que hacer el front ?
    public boolean equalPassword (User userFound, User userLogIn, UserLog log) throws SQLException {
        UserLogManager manager = new UserLogManager(new UserLogMapper());
        boolean value = false;

        int attempt = 0;
        while (attempt < MAX_ATTEMPT && !value){
            if(userFound.getPassword().equals(userLogIn.getPassword())) {
                value = true;
            }
            else {
                attempt++;
                log.setAttemptLogin(attempt);
                manager.update(log);
            }
        }
        return value;
    }

}

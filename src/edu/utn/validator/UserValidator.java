package edu.utn.validator;

import edu.utn.entity.User;
import edu.utn.exception.EmailException;
import edu.utn.exception.LastNameException;
import edu.utn.exception.NameException;
import edu.utn.exception.NickNameException;

public class UserValidator {

    private static final String NAME_EXCEPTION = "NAME EXCEPTION";
    private static final String LAST_NAME_EXCEPTION = "LAST NAME EXCEPTION";
    private static final String EMAIL_EXCEPTION = "EMAIL EXCEPTION";
    private static final String NICKNAME_EXCEPTION = "NICKNAME EXCEPTION";

    public void isValid (User user) throws NameException, LastNameException, EmailException, NickNameException {
        isValidName(user);
        isValidLastName(user);
        isValidEmail(user);
        isValidNickName(user);
    }

    private void isValidName (User user) throws NameException {
        boolean value = user.getName() != null && !user.getName().isEmpty();
        value &= insideAlphabet(user);
        if (!value) {
            throw new NameException(NAME_EXCEPTION);
        }
    }

    private void isValidLastName (User user) throws LastNameException {
        boolean value = user.getName() != null && !user.getName().isEmpty();
        value &= insideAlphabet(user);
        if (!value) {
            throw new LastNameException(LAST_NAME_EXCEPTION);
        }
    }

    private void isValidEmail (User user) throws EmailException {
        boolean value  = user.getEmail() != null && !user.getEmail().isEmpty();
        if (!value) {
            throw new EmailException(EMAIL_EXCEPTION);
        }
    }

    private void isValidNickName (User user) throws NickNameException {
        boolean value = user.getNickName() != null && !user.getNickName().isEmpty();
        if(!value){
            throw new NickNameException(NICKNAME_EXCEPTION);
        }
    }

    private boolean isWord (String word, int i) {
        return word.charAt(i) > 64 && word.charAt(i) < 91 || word.charAt(i) > 96 && word.charAt(i) < 123;
    }

    private boolean insideAlphabet (User user) {
        String name = user.getName();
        boolean flag = false;
        int i = 0;
        while (i < name.length() && !flag) {
            if (!(isWord(name, i))) {
                flag = true;
            }
            i++;
        }
        return flag;
    }
}

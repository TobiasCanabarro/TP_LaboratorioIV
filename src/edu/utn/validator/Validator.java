package edu.utn.validator;

import edu.utn.exception.EmailException;

public class Validator {

    private static final String EMAIL_EXCEPTION = "EMAIL EXCEPTION";

    protected void isValidEmail(String email) throws EmailException {
        boolean value  = email != null && !email.isEmpty();
        if (!value) {
            throw new EmailException(EMAIL_EXCEPTION);
        }
    }

    protected boolean isWord (String word, int i) {
        return word.charAt(i) > 64 && word.charAt(i) < 91 || word.charAt(i) > 96 && word.charAt(i) < 123;
    }

    protected boolean insideAlphabet(String name) {
        boolean flag = true;
        int i = 0;
        while (i < name.length() && flag) {
            if (!(isWord(name, i))) {
                flag = false;
            }
            i++;
        }
        return flag;
    }

    protected boolean isAlphaNumeric (String password) {
        boolean value = false;
        for (int i = 0; i < password.length() && !value; i++) {
            if(!isWord(password, i) || !isNumber(password, i)){
                value = true;
            }
        }
        return value;
    }

    protected boolean isNumber (String number, int i) {
        return  number.charAt(i) > 47 && number.charAt(i) < 58;
    }


}

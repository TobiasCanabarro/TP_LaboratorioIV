package edu.utn.validator;

import edu.utn.exception.EmailException;

public class Validator {

    protected boolean isValidEmail(String email) {
        return email != null && !email.isEmpty();
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
        boolean value = true;
        for (int i = 0; i < password.length() && value; i++) {//tocan123
            if(!isWord(password, i) && !isNumber(password, i)){
                value = false;
            }
        }
        return value;
    }

    protected boolean isNumber (String number, int i) {
        return  number.charAt(i) > 47 && number.charAt(i) < 58;
    }


}

package edu.utn.validator;

public class Validator <T>{

    public boolean isNull (T object){
        return object == null;
    }

//    protected boolean isWord (String word, int i) {
//        return word.charAt(i) > 64 && word.charAt(i) < 91 || word.charAt(i) > 96 && word.charAt(i) < 123;
//    }
//
//    protected boolean isNumber (String number, int i) {
//        return  number.charAt(i) > 47 && number.charAt(i) < 58;
//    }
//

//
//
//    protected boolean insideAlphabet(String name) {
//        boolean flag = true;
//        int i = 0;
//        while (i < name.length() && flag) {
//            if (!(isWord(name, i))) {
//                flag = false;
//            }
//            i++;
//        }
//        return flag;
//    }
//
//    protected boolean isAlphaNumeric (String password) {
//        boolean value = true;
//        for (int i = 0; i < password.length() && value; i++) {
//            if(!isWord(password, i) && !isNumber(password, i)){
//                value = false;
//            }
//        }
//        return value;
//    }




}

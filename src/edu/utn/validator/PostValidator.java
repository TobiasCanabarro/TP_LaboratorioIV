package edu.utn.validator;

public class PostValidator {

    private static final int MAX_CHAR = 140;

    public boolean IsValidLength(String post) {
        return post.length() <=  MAX_CHAR;
    }
}

package edu.utn.enums;

public enum LogInResult {

    ERR_AUTHENTICATION("Error of authentication"),  LOCKED_ACCOUNT("Locked account"), OK("Log in successful"),
    ERR_USER_DOES_NOT_EXIST ("Error user does not exist"), ERR_USER_IS_ALREADY_LOGGED_IN ("Error the user is already logged in"),
    ERR("Error"), ERR_IS_LOCKED("Error the user is locked"), UNLOCKED_ACCOUNT ("Unlocked account");

    private String description;

    LogInResult(String description){
        setDescription(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

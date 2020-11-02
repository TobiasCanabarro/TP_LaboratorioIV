package edu.utn.enums;

public enum Result {

    ERR_AUTHENTICATION("Error of authentication"),  LOCKED_ACCOUNT("Locked account"), LOG_IN_OK("Log in successful"),
    ERR_USER_DOES_NOT_EXIST ("Error user does not exist"), ERR_USER_IS_ALREADY_LOGGED_IN ("Error the user is already logged in"),
    ERR("Error"), ERR_IS_LOCKED("Error the user is locked"), UNLOCKED_ACCOUNT ("Unlocked account"),
    LOG_OUT_OK("Log out successful"), CHANGE_PASSWORD("Change password"), SIGN_IN_OK("Sign in successful"),
    SEND_REQUEST_OK("Send request successful"), ACCEPT_REQUEST_OK("Accept request successful"), REFUSE_REQUEST_OK ("Refuse request successful");

    private String description;

    Result(String description){
        setDescription(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

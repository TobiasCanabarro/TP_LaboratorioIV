//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package edu.utn.enums;

import edu.utn.entity.User;

//Es un ENUM, con resultados; se usa para agregarle una descripcion al JSON y en algunos casos puede tener un User (esto no seria lo mas adecuado)
public enum Result {
    ERR_AUTHENTICATION("Error of authentication"),
    LOCKED_ACCOUNT("Locked account"),
    LOG_IN_OK("Log in successful"),
    ERR_USER_DOES_NOT_EXIST("Error user does not exist"),
    ERR_USER_IS_ALREADY_LOGGED_IN("Error the user is already logged in"),
    ERR("ERROR"),
    ERR_IS_LOCKED("Error the user is locked"),
    UNLOCKED_ACCOUNT("Unlocked account"),
    LOG_OUT_OK("Log out successful"),
    CHANGE_PASSWORD("Change password"),
    SIGN_IN_OK("Sign in successful"),
    SEND_REQUEST_OK("Send request successful"),
    ACCEPT_REQUEST_OK("Accept request successful"),
    REFUSE_REQUEST_OK("Refuse request successful"),
    LOG_OUT_FAIL("Logout fail"),
    SIGN_IN_FAIL("Sign in successful"),
    UNLOCKED_ACCOUNT_OK("Unlocked account successful"),
    UNLOCKED_ACCOUNT_FAIL("Unlocked account fail"),
    REQUEST_ACCOUNT_OK("Request account successful"),
    REQUEST_ACCOUNT_FAIL("Request account fail"),
    OK("OK"),
    RESET_PASSWORD("Reset password"),
    CHANGE_PASSWORD_FAIL("Change password fail");

    private String description;
    private User user;

    private Result(String description) {
        this.setDescription(description);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package edu.utn.entity;

import java.sql.Date;

public class UserLog {

    private long id;
    private String email;
    private boolean login;
    private int attemptLogin;
    private boolean locked;
    private long userId;
    private java.sql.Date lastLogin;

    public UserLog(String email, long userId) {
        setEmail(email);
        setLogin(false);
        setAttemptLogin(0);
        setLocked(false);
        setUserId(userId);
        setLastLogin(new Date(9999));
    }

    // Cuando se obtiene los datos de la DB se usa este constructor
    public UserLog(long id, String email, boolean login, int attemptLogin, boolean locked, long userId, Date lastLogin) {
        setId(id);
        setEmail(email);
        setLogin(login);
        setAttemptLogin(attemptLogin);
        setLocked(locked);
        setUserId(userId);
        setLastLogin(lastLogin);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public int getAttemptLogin() {
        return attemptLogin;
    }

    public void setAttemptLogin(int attemptLogin) {
        this.attemptLogin = attemptLogin;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}

package edu.utn.entity;

public class requestRelation {

    private long id;
    private String name;
    private String surname;
    private String email;
    private long friendUserId;
    private long userId;

    public requestRelation(long id, String name, String surname, String email, long friendUserId, long userId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.friendUserId = friendUserId;
        this.userId = userId;
    }

    public requestRelation(String name, String surname, String email, long friendUserId, long userId) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.friendUserId = friendUserId;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(long friendUserId) {
        this.friendUserId = friendUserId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}

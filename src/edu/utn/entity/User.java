package edu.utn.entity;

import java.util.Date;

public class User {
    private String name;
    private String lastName;
    private String nickName;
    private String email;
    private Date birthday;

    public User(String name, String lastname, String email, Date birthday, String nickname) {
        setName(name);
        setLastName(lastname);
        setNickName(nickname);
        setEmail(email);
        setBirthday(birthday);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String feature () {
        return getName() + " " + getLastName()
                + " " + getEmail() + " " + getBirthday().toString() + " " + getNickName();
    }
}

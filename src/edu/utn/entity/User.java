package edu.utn.entity;

import java.util.Date;

public class User {
    private long id;
    private String name;
    private String password;
    private String surname;
    private String email;
    private String nickname;
    private Date birthday;
    private long publicationId;

    public User(String name, String password,String surname, String email,String nickname, Date birthday) {
        setName(name);
        setPassword(password);
        setSurname(surname);
        setEmail(email);
        setBirthday(birthday);
        setNickname(nickname);
    }

    // Cuando se obtiene los datos de la DB se usa este constructor
    public User(long id, String name, String password,String surname, String email,String nickname, Date birthday, long publicationId) {
        setId(id);
        setName(name);
        setPassword(password);
        setSurname(surname);
        setEmail(email);
        setBirthday(birthday);
        setNickname(nickname);
        setPublicationId(publicationId);
    }


    public String feature () {
        return getName() + " " + getSurname()
                + " " + getEmail() + " " + getBirthday().toString() + " " + getNickname();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(long publicationId) {
        this.publicationId = publicationId;
    }
}

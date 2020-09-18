package edu.utn.dto;

import edu.utn.entity.User;

import java.util.Date;

public class UserDto {

    private int id; // puede ser el id del registro
    private String name;
    private String lastName;
    private String nickName;
    private String email;
    private Date birthday;

    public UserDto (User user){
        setName(user.getName());
        setLastName(user.getLastName());
        setBirthday(user.getBirthday());
        setEmail(user.getEmail());
        setNickName(user.getNickName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}

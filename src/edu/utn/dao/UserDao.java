package edu.utn.dao;

import edu.utn.entity.User;

public class UserDao extends DataAccess {

    private UserDao(String host, String port, String user, String password){
        super(host, port, user, password);
    }

    public UserDao getUserDao(String host, String port, String user, String password){
        super.getDataAccess(host,  port, user, password);
    }
}

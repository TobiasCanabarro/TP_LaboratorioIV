package edu.utn;

import edu.utn.entity.User;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.UserManager;

import java.sql.Date;

public class Main {

    public static void main(String[] args) {//ALTER SEQUENCE user_log_id_seq RESTART WITH 1
        UserManager manager = UserManagerFactory.create();
        User user = new User("Tobias", "tobi123", "Canabarro", "tobias@gmail.com", "tobi", new Date(99999));
        boolean value = manager.logIn(user.getEmail(), user.getPassword());
        System.out.println(value);
    }


}

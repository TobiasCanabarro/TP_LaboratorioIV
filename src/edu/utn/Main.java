package edu.utn;

import edu.utn.entity.User;
import edu.utn.enums.Result;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.UserManager;

import java.sql.Date;


public class Main {

    public static void main(String[] args) {
        User user = new User ("Muten", "mutenR123", "roshi", "muten@gmail.com", "Maestro Roshi", new Date(1));
        UserManager manager = UserManagerFactory.create();
        boolean value = manager.signIn(user);

        System.out.println(value);

    }
}

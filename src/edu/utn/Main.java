package edu.utn;
import edu.utn.entity.User;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.UserManager;

import java.sql.Date;


public class Main {

    public static void main(String[] args){//ALTER SEQUENCE user_id_seq RESTART WITH 1
       User user  = new User("Pepe", "pepe123", "Perez", "pepe@gmail.com", "pepe", new Date(99999));

        UserManager manager = UserManagerFactory.create();
        boolean v = manager.signIn(user);
        System.out.println(v);


    }


}

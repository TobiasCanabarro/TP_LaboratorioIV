package edu.utn;
import edu.utn.enums.Result;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.UserManager;

import javax.mail.MessagingException;


public class Main {

    public static void main(String[] args) throws MessagingException {//ALTER SEQUENCE user_id_seq RESTART WITH 1
       //User user  = new User("Ignacio", "nacho123", "Mo", "ignacion@gmail.com", "nacho", new Date(99999));

        UserManager manager = UserManagerFactory.create();
        Result v = manager.logIn("roberto@gmail.com", "roberto123");
        System.out.println(v.getDescription());


    }


}

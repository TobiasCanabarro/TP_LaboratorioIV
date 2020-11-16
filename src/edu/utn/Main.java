package edu.utn;

import edu.utn.entity.User;
import edu.utn.enums.Result;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.UserManager;

import javax.mail.MessagingException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws MessagingException {

        UserManager manager = UserManagerFactory.create();
        Result value = manager.logIn("tomas@gmail.com", "tomas123");

        System.out.println(value.getDescription());

    }
}

package edu.utn;

import edu.utn.entity.User;
import edu.utn.enums.Result;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.UserManager;

import javax.mail.MessagingException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws MessagingException {
	// write your code here

        UserManager manager = UserManagerFactory.create();
        //boolean value = manager.requestUnlockedAccount("tobiascanabarro@gmail.com", "http:localhost:8080/webapi/login/unLockedAccount/" + "tobiascanabarro@gmail.com");
        Result value = manager.logIn("tobiascanabarro@gmail.com", "aaaa");
        System.out.println(value.getDescription());

    }
}

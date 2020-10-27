package edu.utn;

import edu.utn.entity.RequestRelationship;
import edu.utn.entity.User;
import edu.utn.factory.RequestRelationshipManagerFactory;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.RequestRelationshipManager;
import edu.utn.manager.UserManager;

import java.sql.Date;

public class Main {

    public static void main(String[] args) {//ALTER SEQUENCE user_id_seq RESTART WITH 1
        User userSend = new User("Pablo", "pablo123", "Forcinito", "pablo@gmail.com", "forci", new Date(99999));
        User userReceive = new User("Tobias", "tobi123", "Canabarro", "tobias@gmail.com", "tobi", new Date(99999));
        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
        boolean value = manager.sendRequest(userSend.getEmail(), userReceive.getEmail());


        System.out.println(value);

    }


}

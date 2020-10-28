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
//        User userSend = new User("Pablo", "pablo123", "Forcinito", "pablo@gmail.com", "forci", new Date(99999));
        User userReceive = new User("Tobias", "tobias123", "Canabarro", "tobias@gmail.com", "tobi", new Date(99999));
//        boolean value = manager.sendRequest( userReceive.getEmail(), userSend.getEmail());
//
//        RequestRelationshipManager requestManager = RequestRelationshipManagerFactory.create();
        UserManager userManager = UserManagerFactory.create();
//        User userReceive = userManager.get(user2.getEmail());
//        User userSend = userManager.get(user1.getEmail());
//
//        RequestRelationship relationship = requestManager.get(userReceive.getId(), userSend.getId());
//        boolean value = requestManager.acceptRequest(userReceive.getEmail(), userSend.getEmail());
        boolean value = userManager.update(userReceive);
        System.out.println(value);

    }


}

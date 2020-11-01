package edu.utn;

import edu.utn.entity.RequestRelationship;
import edu.utn.entity.User;
import edu.utn.entity.UserPost;
import edu.utn.factory.RequestRelationshipManagerFactory;
import edu.utn.factory.UserPostManagerFactory;
import edu.utn.helper.DateHelper;
import edu.utn.manager.PostManager;


import java.sql.Date;
import java.util.List;


public class Main {

    public static void main(String[] args){//ALTER SEQUENCE user_id_seq RESTART WITH 1
         User userReceive = new User("Pablo", "pablo123", "Forcinito", "pablo@gmail.com", "forci", new Date(99999));
         User userSend  = new User("Tobias", "tobias123", "Canabarro", "tobias@gmail.com", "tobi", new Date(99999));
         User user = new User("Carlos", "carlos123", "Capozucca", "carlos@gmail.com", "negro", new Date(9999));

        PostManager manager = UserPostManagerFactory.create();
        List<UserPost> posts = manager.myPosts(14);

        for(UserPost element : posts){
            System.out.println(element.getIdUser());
            System.out.println(element.getPost());
            System.out.println(element.getDatePublication());
        }



    }


}

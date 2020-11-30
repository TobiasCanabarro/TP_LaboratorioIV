package edu.utn;

import edu.utn.entity.RequestRelationship;
import edu.utn.entity.User;
import edu.utn.entity.UserPost;
import edu.utn.enums.Result;
import edu.utn.factory.RequestRelationshipManagerFactory;
import edu.utn.factory.UserManagerFactory;
import edu.utn.factory.UserPostManagerFactory;
import edu.utn.log.LogHelper;
import edu.utn.manager.PostManager;
import edu.utn.manager.RequestRelationshipManager;
import edu.utn.manager.UserManager;

import java.sql.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) {

//        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
//
//        List<RequestRelationship> myRequest = manager.getAllRequest(14);
//
//        for (RequestRelationship element : myRequest) {
//            System.out.println(element.getId());
//            System.out.println(element.getIdUserReceive());
//            System.out.println(element.getIdUserSend());
//        }

        PostManager manager = UserPostManagerFactory.create();

        List<UserPost> posts = manager.myPosts(1);

        for (UserPost post : posts) {
            System.out.println(post.getIdUser());
            System.out.println(post.getPost());
        }

    }
}

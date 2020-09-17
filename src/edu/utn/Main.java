package edu.utn;

import edu.utn.entity.User;
import edu.utn.manager.UserManager;
import edu.utn.mapper.UserMapper;

import java.sql.SQLException;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        postUser ();
    }

    //Registry user :D
    private static void postUser () {
        try {
            UserMapper userMapper = new UserMapper();
            UserManager userManager = new UserManager(userMapper);
            boolean success = userManager.save(new User("Tobias", "Canabarro", "tobi",
                    "tobias@gmail.com", null));
            System.out.println(success);

        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}

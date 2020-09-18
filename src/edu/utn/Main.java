package edu.utn;

import edu.utn.entity.User;
import edu.utn.manager.UserManager;
import edu.utn.mapper.UserMapper;

import java.sql.SQLException;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        getUser();
    }

    //Registry user :D
    private static void postUser () {
        try {
            UserMapper userMapper = new UserMapper();
            UserManager userManager = new UserManager(userMapper);//String name, String lastname, String email, Date birthday, String nickname
            boolean success = userManager.save(new User("Tobias", "Canabarro", "tobias@gmail.com",
                    null, "tobi"));
            System.out.println(success);

        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    private static void getUser () {
        UserMapper userMapper = new UserMapper();
        UserManager userManager = new UserManager(userMapper);
        User user = userManager.get("tobias@gmail.com"); //key para buscar el registro. Esta hardcodeado el email para la busqueda
        System.out.println(user.feature());
    }

}

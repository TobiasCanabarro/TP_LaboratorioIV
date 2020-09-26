package edu.utn;

import edu.utn.entity.SignIn;
import edu.utn.entity.User;
import edu.utn.entity.UserLog;
import edu.utn.manager.UserLogManager;
import edu.utn.manager.UserManager;
import edu.utn.mapper.UserLogMapper;
import edu.utn.mapper.UserMapper;

import java.sql.Date;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

    }

    //tendrian que ser test unitarios xD

    private static void signInWithTransaction () {
        UserLogManager userLogManager = new UserLogManager(new UserLogMapper());
        User user = new User("Roberto", "roberto123", "Zacarello", "roberto@gmail.com", "rober", new Date(8888), 4);
        boolean value = userLogManager.saveWithTransaction(user);//(String email, long userId, Date lastLogin
        System.out.println(value);
    }


    private static void signIn () {
        SignIn signIn = new SignIn(new UserManager(new UserMapper()));
        boolean value = signIn.signIn((new User("Tobias", "tobias123",
                "Canabarro", "tobias@gmail.com", "Tobi", new Date(9999), 1)));
        System.out.println(value);
    }

    private static void signIn2 () {
        SignIn signIn = new SignIn(new UserManager(new UserMapper()));
        boolean value = signIn.signIn((new User("Pablo", "pablo123",
                "Forcinito", "pablo@gmail.com", "Forci", new Date(9999), 2)));
        System.out.println(value);
    }

    private static void signIn3 () {
        SignIn signIn = new SignIn(new UserManager(new UserMapper()));
        boolean value = signIn.signIn((new User("Ignacio", "ignacio123",
                "Ortiz", "ignacio@gmail.com", "Nacho", new Date(9999), 2)));
        System.out.println(value);
    }


    private static void postUser () {
        try {
            UserMapper userMapper = new UserMapper();
            UserManager userManager = new UserManager(userMapper);//String name, String surname, String email, String nickname, Date birthday, int publicationId
            boolean success = userManager.save(new User("Tobias", "tobias123", "Canabarro", "tobias@gmail.com", "Tobi",
                    new Date(9999), 1));
            System.out.println(success);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static void getUser () {
        UserMapper userMapper = new UserMapper();
        UserManager userManager = new UserManager(userMapper);
        User user = userManager.get("tobias@gmail.com"); //key para buscar el registro. Esta hardcodeado el email para la busqueda
        System.out.println(user.feature());
    }

}

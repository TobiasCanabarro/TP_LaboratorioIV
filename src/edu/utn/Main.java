package edu.utn;

import edu.utn.entity.LogIn;
import edu.utn.entity.SignIn;
import edu.utn.entity.User;
import edu.utn.entity.UserLog;
import edu.utn.factory.UserLogManagerFactory;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.UserLogManager;
import edu.utn.manager.UserManager;
import edu.utn.mapper.UserLogMapper;
import edu.utn.mapper.UserMapper;

import java.sql.Date;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {//ALTER SEQUENCE user_log_id_seq RESTART WITH 1
        singIn5();
    }

    //tendrian que ser test unitarios xD

    private static void logOut (){
        User user = new User("Tobias", "tobias123", "Canabarro", "tobias@gmail.com", "Tobi", new Date(9999));
        UserLog userLog = new UserLog(user.getEmail(), user.getId(), new Date(999999));
        LogIn logIn = new LogIn(UserManagerFactory.create(user), UserLogManagerFactory.create(userLog));
        boolean value = logIn.logOut();
        System.out.println(value);
    }

    private static void login() {
        User user = new User("Tobias", "tobias123", "Canabarro", "tobias@gmail.com", "Tobi", new Date(9999));
        LogIn logIn = new LogIn(UserManagerFactory.create(user));
        boolean value = logIn.logIn();
        System.out.println(value);
    }

    private static void signIn() {//String name, String password,String surname, String email,String nickname, Date birthday
        User user = new User("Tobias", "tobias123",
                "Canabarro", "tobias@gmail.com", "Tobi", new Date(9999));
        SignIn signIn = new SignIn(UserManagerFactory.create(user));
        boolean value = signIn.signIn();
        System.out.println(value);
    }

    private static void signIn2() {
        User user = new User("Pablo", "pablo123",
                "Forcinito", "pablo@gmail.com", "Forci", new Date(9999));
        SignIn signIn = new SignIn(UserManagerFactory.create(user));
        boolean value = signIn.signIn();
        System.out.println(value);
    }

    private static void signIn3() {
        User user = new User("Ignacio", "ignacio123",
                "Ortiz", "ignacio@gmail.com", "Nacho", new Date(9999));
        SignIn signIn = new SignIn(UserManagerFactory.create(user));
        boolean value = signIn.signIn();
        System.out.println(value);
    }

    private static void singIn4() {
        User user = new User("Carlos", "carlos123",
                "Capozucca", "carlos@gmail.com", "Negro", new Date(9999));
        SignIn signIn = new SignIn(UserManagerFactory.create(user));
        boolean value = signIn.signIn();
        System.out.println(value);
    }

    private static void singIn5(){
        User user = new User ("Juan", "perez123", "Perez", "juan@gmail.com", "juancito", new Date(999999));
        SignIn signIn = new SignIn(UserManagerFactory.create(user));
        boolean value = signIn.signIn();
        System.out.println(value);
    }


}

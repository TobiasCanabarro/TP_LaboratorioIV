package edu.utn;

import edu.utn.entity.User;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.UserManager;
import java.sql.Date;

public class Main {

    public static void main(String[] args) {//ALTER SEQUENCE user_log_id_seq RESTART WITH 1
        login();
    }

    //tendrian que ser test unitarios xD

    private static void logOut (){
        User user = new User("Tobias", "tobias123", "Canabarro", "tobias@gmail.com", "Tobi", new Date(9999));
        UserManager userManager = UserManagerFactory.create(user);
        boolean value = userManager.logOut();
        System.out.println(value);
    }

    private static void logOut2(){
        User user = new User ("Juan", "perez123", "Perez", "juan@gmail.com", "juancito", new Date(999999));
        UserManager userManager = UserManagerFactory.create(user);
        boolean value = userManager.logOut();
        System.out.println(value);
    }

    private static void login() {
        User user = new User("Tobias", "tobias123", "Canabarro", "tobias@gmail.com", "Tobi", new Date(9999));
        UserManager userManager = UserManagerFactory.create(user);
        boolean value = userManager.logIn();
        System.out.println(value);
    }

    private static void login2(){
        User user = new User ("Juan", "perez123", "Perez", "juan@gmail.com", "juancito", new Date(999999));
        UserManager userManager = UserManagerFactory.create(user);
        boolean value = userManager.logIn();
        System.out.println(value);
    }

    private static void signIn() {//String name, String password,String surname, String email,String nickname, Date birthday
        User user = new User("Tobias", "tobias123",
                "Canabarro", "tobias@gmail.com", "Tobi", new Date(9999));
        UserManager userManager = UserManagerFactory.create(user);
        boolean value = userManager.signIn();
        System.out.println(value);
    }

    private static void signIn2() {
        User user = new User("Pablo", "pablo123",
                "Forcinito", "pablo@gmail.com", "Forci", new Date(9999));
        UserManager userManager = UserManagerFactory.create(user);
        boolean value = userManager.signIn();
        System.out.println(value);
    }

    private static void signIn3() {
        User user = new User("Ignacio", "ignacio123",
                "Ortiz", "ignacio@gmail.com", "Nacho", new Date(9999));
        UserManager userManager = UserManagerFactory.create(user);
        boolean value = userManager.signIn();
        System.out.println(value);
    }

    private static void singIn4() {
        User user = new User("Carlos", "carlos123",
                "Capozucca", "carlos@gmail.com", "Negro", new Date(9999));
        UserManager userManager = UserManagerFactory.create(user);
        boolean value = userManager.signIn();
        System.out.println(value);
    }

    private static void singIn5(){
        User user = new User ("Juan", "perez123", "Perez", "juan@gmail.com", "juancito", new Date(999999));
        UserManager userManager = UserManagerFactory.create(user);
        boolean value = userManager.signIn();
        System.out.println(value);
    }

    private static void signIn6(){
        User user = new User ("Pedro", "pedro123","Picapiedra", "pedro@gmail.com", "pedrito", new Date(666));
        UserManager userManager = UserManagerFactory.create(user);
        boolean value = userManager.signIn();
        System.out.println(value);
    }

}

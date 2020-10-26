package test.edu.utn.dao;

import edu.utn.entity.User;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.UserManager;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    /*
    @Test
    public void saveOk() {
        User user = new User("Mariano", "hadouken123", "Kaimakamian",
                "mariano@gmail.com", "marian", new Date(9999));
        UserManager userManager = UserManagerFactory.create();
        boolean value = userManager.save(user);
        assertEquals(true, value);
    }

    @Test
    public void saveFail() {
        User user = new User("Mariano", "hadouken123", "Kaimakamian",
                "", "marian", new Date(9999));
        UserManager userManager = UserManagerFactory.create();
        boolean value = userManager.save(user);
        assertEquals(false, value);
    }

    @Test
    public void getOk () {
        User user = new User("Mariano", "hadouken123", "Kaimakamian",
                "mariano@gmail.com", "marian", new Date(9999));
        UserManager userManager = UserManagerFactory.create(user);
        User foundUser = userManager.get();
        assertEquals(true, user.getEmail().equals(foundUser.getEmail()));
    }

    @Test
    public void getFail (){
        User user = new User("Mariano", "hadouken123", "Kaimakamian",
                "kaimakamian@gmail.com", "marian", new Date(9999));
        UserManager userManager = UserManagerFactory.create();
        boolean value = false;
        try {
            User foundUser = userManager.get(user.getEmail());
            value = user.getEmail().equals(foundUser.getEmail());
        }catch (NullPointerException ex){
            value = false;
        }
        assertEquals(false, value);
    }

    @Test
    public void updateOk(){
        User user = new User("Mariano", "hadouken123", "Kaimakamian Carrau",
                "mariano@gmail.com", "marian", new Date(9999));
        UserManager userManager = UserManagerFactory.create();
        boolean value =  userManager.update(user.getEmail());
        assertEquals(true, value);
    }

    @Test
    public void updateFail(){
        User user = new User("Mariano", "hadouken123", "Kaimakamian Carrau",
                "mkc@gmail.com", "marian", new Date(9999));
        UserManager userManager = UserManagerFactory.create();
        boolean value = userManager.update(user.getEmail());
        assertEquals(false, value);
    }

    */
}
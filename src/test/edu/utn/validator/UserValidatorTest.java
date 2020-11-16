package test.edu.utn.validator;

import edu.utn.entity.User;
import edu.utn.validator.UserValidator;
import mock.edu.utn.factory.UserManagerFactoryMock;
import mock.edu.utn.manager.UserManagerMock;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {


    @Test
    void isValidUser() {

        User user = new User("tobias", "tobias123", "canabarro", "tobias@gmail.com", "tobi", new Date(9999));
        UserValidator validator = new UserValidator();
        boolean value = validator.isValidUser(user);

        assertEquals(true, value);
    }

    @Test
    void isValidUserFail() {

        User user = new User("", "tobias123", "canabarro", "tobias@gmail.com", "tobi", new Date(9999));
        UserValidator validator = new UserValidator();
        boolean value = validator.isValidUser(user);

        assertEquals(false, value);
    }

    @Test
    void isValidPassword() {

        String password = "tobias123";
        UserValidator validator = new UserValidator();
        boolean value = validator.isValidPassword(password);

        assertEquals(true, value);
    }

    @Test
    void isValidPasswordFail() {

        String password = "tobias123_";
        UserValidator validator = new UserValidator();
        boolean value = validator.isValidPassword(password);

        assertEquals(false, value);
    }

    @Test
    void existsUser() {

        String email = "tobias@gmail.com";
        UserManagerMock managerMock = UserManagerFactoryMock.create();
        managerMock.getValidatorMock().setValid(true);
        User userFound = managerMock.get(email);
        boolean value = userFound != null;

        assertEquals(true, value);

    }

    @Test
    void existsUserFail() {

        String email = "tobias@gmail.com";
        UserManagerMock managerMock = UserManagerFactoryMock.create();
        managerMock.getValidatorMock().setValid(false);
        User userFound = managerMock.get(email);
        boolean value = userFound != null;

        assertEquals(false, value);
    }

    @Test
    void attemptsRemain() {

        User user = new User("tobias", "tobias123", "canabarro", "tobias@gmail.com", "tobi", new Date(9999));
        UserValidator validator = new UserValidator();
        boolean value = validator.attemptsRemain(user);

        assertEquals(true, value);
    }

    @Test
    void attemptsRemainFail() {

        User user = new User("tobias", "tobias123", "canabarro", "tobias@gmail.com", "tobi", new Date(9999));
        user.setAttemptLogin(5);
        UserValidator validator = new UserValidator();
        boolean value = validator.attemptsRemain(user);

        assertEquals(false, value);
    }

    @Test
    void isLocked() {

        User user = new User("tobias", "tobias123", "canabarro", "tobias@gmail.com", "tobi", new Date(9999));
        user.setLocked(true);
        UserValidator validator = new UserValidator();
        boolean value = validator.isLocked(user);

        assertEquals(true, value);

    }

    @Test
    void isLockedFail() {

        User user = new User("tobias", "tobias123", "canabarro", "tobias@gmail.com", "tobi", new Date(9999));
        user.setLocked(false);
        UserValidator validator = new UserValidator();
        boolean value = validator.isLocked(user);

        assertEquals(false, value);
    }

    @Test
    void isLogIn (){

        User user = new User("tobias", "tobias123", "canabarro", "tobias@gmail.com", "tobi", new Date(9999));
        user.setLogIn(true);
        UserValidator validator = new UserValidator();
        boolean value = validator.isLogIn(user);

        assertEquals(true, value);
    }

    @Test
    void isLogInFail (){

        User user = new User("tobias", "tobias123", "canabarro", "tobias@gmail.com", "tobi", new Date(9999));
        UserValidator validator = new UserValidator();
        boolean value = validator.isLogIn(user);

        assertEquals(true, value);
    }
}
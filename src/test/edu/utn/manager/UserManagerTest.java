package test.edu.utn.manager;

import edu.utn.entity.User;
import mock.edu.utn.factory.UserManagerFactoryMock;
import mock.edu.utn.manager.UserManagerMock;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {

    @Test
    void signInOk(){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.getValidatorMock().setValid(true);
        boolean value = manager.getValidatorMock().isValid(user.getEmail());
        if(value){
            value &= manager.save(user);
        }
        assertEquals(true, value);
    }

    @Test
    void signInFail(){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.getValidatorMock().setValid(false);
        boolean value = manager.getValidatorMock().isValid(user.getEmail());
        if(value){
            value &= manager.save(user);
        }
        assertEquals(false, value);
    }

    @Test
    void logInOk(){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.getValidatorMock().setValid(true);
        boolean value = manager.getValidatorMock().isValid(user.getEmail());
        if(value){
            value &= manager.update(user);
        }
        assertEquals(true, value);
    }

    @Test
    void logInFail(){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.getValidatorMock().setValid(false);
        boolean value = manager.getValidatorMock().isValid(user.getEmail());
        if(value){
            value &= manager.update(user);
        }
        assertEquals(false, value);
    }


    /*
    @Test
    void changePasswordOk() throws PasswordException {
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        String newPassword = "tocan123";
        ChangePassword changePassword = new ChangePassword();
        boolean value = changePassword.changePassword(user, newPassword);
        assertEquals(true, value);
    }

    @Test
    void changePasswordFail() throws PasswordException {
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        String newPassword = "tocan_123";
        ChangePassword changePassword = new ChangePassword();
        boolean value = false;
        try {
            value = changePassword.changePassword(user, newPassword);
        }
        catch (PasswordException ex){
            value = false;
        }
        assertEquals(false, value);
    }

    @Test
    void logOutOk() {
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        LogInManager logInManager = new LogInManager();
        boolean value = logInManager.logOut(user);
        assertEquals(true, value);
    }

    @Test
    void logOutFail() {
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        LogInManager logInManager = new LogInManager();
        boolean value = logInManager.logOut(user);
        assertEquals(false, value);
    }

    @Test
    void saveOk(){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.setValidMapper(true);
        boolean value = manager.save(user);
        assertEquals(true, value);
    }

    @Test
    void saveFail(){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.setValidMapper(false);
        boolean value = manager.save(user);
        assertEquals(false, value);
    }

    @Test
    void updateOk(){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create(user);
        manager.setValidMapper(true);
        boolean value = manager.update();
        assertEquals(true, value);
    }

    @Test
    void getOk(){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create(user);
        manager.setValidMapper(true);
        User foundUser = manager.get();
        boolean value = foundUser != null;
        assertEquals(true, value);
    }

    @Test
    void getFail(){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create(user);
        manager.setValidMapper(false);
        User foundUser = manager.get();
        boolean value = foundUser != null;
        assertEquals(false, value);
    }
    */


}
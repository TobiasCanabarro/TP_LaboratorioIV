package test.edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.enums.Result;
import mock.edu.utn.factory.UserManagerFactoryMock;
import mock.edu.utn.manager.MailMock;
import mock.edu.utn.manager.UserManagerMock;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserManagerTest{

    @Test
    void signInOk(){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.getValidatorMock().setValid(true);
        boolean value = manager.getValidatorMock().isValid(user);
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
        boolean value = manager.getValidatorMock().isValid(user);
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
        boolean value = manager.getValidatorMock().isValid(user);
        if(value){
            user.setLogIn(true);
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
        boolean value = manager.getValidatorMock().isValid(user);
        if(value){
            user.setLogIn(true);
            value &= manager.update(user);
        }
        assertEquals(false, value);
    }

    @Test
    void logOutOk() {
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.getValidatorMock().setValid(true);
        boolean value = manager.getValidatorMock().isValid(user);
        if(value){
            user.setLogIn(false);
            value &= manager.update(user);
        }
        assertEquals(true, value);
    }


    @Test
    void logOutFail() {
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.getValidatorMock().setValid(false);
        boolean value = manager.getValidatorMock().isValid(user);
        if(value){
            user.setLogIn(false);
            value &= manager.update(user);
        }
        assertEquals(false, value);

    }

    @Test
    void requestUnlockedAccountOk (){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        String endpoint = "localhost:8080/TP_Laboratorio/rest/unlockedAccount";
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.getValidatorMock().setValid(true);
        boolean value = manager.getValidatorMock().isValid(user);
        if(value){
            MailMock.sendMail("pablo@gmail.com", Result.UNLOCKED_ACCOUNT, "Ingrese a esta ruta para desbloquear su cuenta" + endpoint);
        }
        assertEquals(true, value);
    }

    @Test
    void requestUnlockedAccountFail (){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        String endpoint = "localhost:8080/TP_Laboratorio/rest/unlockedAccount";
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.getValidatorMock().setValid(false);
        boolean value = manager.getValidatorMock().isValid(user);
        if(value){
            MailMock.sendMail("pablo@gmail.com", Result.UNLOCKED_ACCOUNT, "Ingrese a esta ruta para desbloquear su cuenta" + endpoint);
        }
        assertEquals(false, value);
    }

    @Test
    void saveOk(){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.getValidatorMock().setValid(true);
        boolean value = manager.save(user);
        assertEquals(true, value);
    }

    @Test
    void saveFail(){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.getValidatorMock().setValid(false);
        boolean value = manager.save(user);
        assertEquals(false, value);
    }

    @Test
    void updateOk(){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.getValidatorMock().setValid(true);
        boolean value = manager.update(user);
        assertEquals(true, value);
    }

    @Test
    void getOk(){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.getValidatorMock().setValid(true);
        User foundUser = manager.get(user.getEmail());
        boolean value = foundUser != null;
        assertEquals(true, value);
    }

    @Test
    void getFail(){
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserManagerMock manager = UserManagerFactoryMock.create();
        manager.setValidMapper(false);
        User foundUser = manager.get((user.getEmail()));
        boolean value = foundUser != null;
        assertEquals(false, value);
    }


}
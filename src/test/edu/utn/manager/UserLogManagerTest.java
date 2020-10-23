package test.edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.entity.UserLog;
import edu.utn.factory.UserLogManagerFactory;
import edu.utn.manager.UserLogManager;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserLogManagerTest {

    @Test
    void createUserLog() {
        User user = new User("Tobias", "tobias123", "Canabarro",
                "tobias@gmail.com", "Tobi", new Date(9999));
        UserLog userLog = new UserLog(user.getEmail(), 1);
        UserLogManager userLogManager = UserLogManagerFactory.create(userLog);
        boolean value = userLogManager.save();
        assertEquals(true, value);
    }
}
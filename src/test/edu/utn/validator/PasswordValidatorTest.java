package test.edu.utn.validator;

import edu.utn.validator.PasswordValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    @Test
    void newPasswordIsValid() {

        String oldPassword = "tobias123";
        String newPassword = "tobi1234";

        PasswordValidator validator = new PasswordValidator();
        boolean value = validator.newPasswordIsValid(oldPassword, newPassword);

        assertEquals(true, value);

    }

    @Test
    void newPasswordIsValidFail() {

        String oldPassword = "tobias123";
        String newPassword = "tobias123";

        PasswordValidator validator = new PasswordValidator();
        boolean value = validator.newPasswordIsValid(oldPassword, newPassword);

        assertEquals(false, value);
    }
}
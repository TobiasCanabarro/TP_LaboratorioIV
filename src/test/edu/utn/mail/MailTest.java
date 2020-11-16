package test.edu.utn.mail;

import edu.utn.enums.Result;
import edu.utn.mail.Mail;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailTest {

    @Test
    void sendMail() {

        String to = "tobiascanabarro@gmail.com";
        Result result = Result.OK;
        String content = "Todo bien!";
        boolean value = Mail.sendMail(to, result, content);

        assertEquals(true, value);
    }


    @Test
    void sendMailFail() {//Esto va a fallar cuando se ingrese un email que no existe

        String to = "tobias@gmail.com";
        String content = "Todo bien!";
        boolean value = Mail.sendMail(to, null, content);

        assertEquals(false, value);
    }
}
package edu.utn.mail;

import edu.utn.enums.Result;
import edu.utn.log.LogHelper;

import javax.mail.*;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {

    private static Properties props;

    //Este metodo envia un email al usuario.
    public static boolean sendMail (String to, Result result, String content) {
        boolean value = false;

        try {
            initialProperties();
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
                }
            });

            Message message = generateMessage(session, to, result.getDescription(), content);
            Transport.send(message);
            value = true;
        }catch (MessagingException exception){
            LogHelper.createNewErrorLog(exception.getMessage());
        }catch (Exception exception){
            LogHelper.createNewErrorLog(exception.getMessage());
        }

        return value;
    }

    //Este metodo inicializa el miembro props, con los datos que se necesita para hacer el envio del email.
    private static void initialProperties () {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        props.put("from", "testapi.tp@gmail.com");
        props.put("username", "testapi.tp@gmail.com");
        props.put("password", "test1046");
    }

    //Este metodo genera el mensage, con los datos que son ingresados en sendMail.
    private static Message generateMessage (Session session, String to, String subject, String content) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(props.getProperty("from")));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(content);
        return message;
    }
}

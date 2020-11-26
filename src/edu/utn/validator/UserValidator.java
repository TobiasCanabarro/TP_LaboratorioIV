package edu.utn.validator;

import edu.utn.entity.User;
import edu.utn.enums.Result;
import edu.utn.factory.UserManagerFactory;
import edu.utn.log.LogHelper;
import edu.utn.mail.Mail;
import edu.utn.manager.UserManager;
import edu.utn.mapper.UserMapper;

import java.util.regex.Pattern;

public class UserValidator extends Validator <User> {

    private static final int MAX_ATTEMPT = 4;

    //Valida todos los atributos del usuario esten en regla, para poder registrarse.
    public boolean isValidUser (User user){

        boolean value = isValidName(user);
        value &= isValidSurname(user);
        value &= isValidEmail(user);
        value &= isValidPassword(user.getPassword());
        if(value) setLoweCase(user);//Puede que esto no tenga que estar aca.
        return value;
    }

    //Valida que cumpla con el formato estandar de un email, mediante el uso del expresion regular.
    private boolean isValidEmail(User user) {

        boolean value = user.getEmail() != null;

        if(value){
            value &= Pattern.matches("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", user.getEmail());
            value &= !user.getEmail().isEmpty();
        }
        return value;
    }

    //Valida que cumpla con el formato, que este formado por solo letras; contemplando las mayusculas mediante el uso de expresion regular.
    private boolean isValidName (User user){

        boolean value = user.getName() != null;

        if(value){
            value &= !user.getName().isEmpty();
            value &= Pattern.matches("[a-zA-Z]+", user.getName());
        }

        return value;
    }

    //Valida que cumpla con el formato, que este formado por solo letras; contemplando las mayusculas mediante el uso de expresion regular.
    private boolean isValidSurname (User user) {

        boolean value = user.getSurname() != null;

        if(value){
            value &= !user.getSurname().isEmpty();
            value &= Pattern.matches("[a-zA-Z]+", user.getSurname());
        }

        return value;
    }

    //Valida que cumpla con el formato, que este formado por letras, numeros o simbolos y que tenga mas de 8 caracteres; mediante el uso de expresion regular.
    public boolean isValidPassword (String password){

        boolean value =  password != null;

        if(value){
            value &= !password.isEmpty();
            value &= Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$", password);
        }

        return value;
    }

    //Verifica si el usuario existe en la base de datos, en caso de que existiera lo retorna.
    public  boolean existsUser (String email) {

        UserManager manager = UserManagerFactory.create();
        User found = manager.get(email);
        return found != null;
    }

    //Verifica si el quedan intentos, en caso de que no posea se bloquea la cuenta.
    public boolean attemptsRemain(User user) {

        boolean value = user.getAttemptLogin() <= MAX_ATTEMPT;
        if(!value){
            user.setLocked(true);
            value &= Mail.sendMail(user.getEmail(), Result.LOCKED_ACCOUNT, "Account Locked!");
        }
        return value;
    }

    public boolean isLocked (User user) {
        return user.isLocked();
    }

    public boolean isLogIn (User user){
        return user.isLogIn();
    }

    private void setLoweCase (User user){
        user.setName(user.getName().toLowerCase());
        user.setSurname(user.getSurname().toLowerCase());
    }

    public User isAlreadyLogin (String email){

        User user = null;

        try {

            UserManager manager = UserManagerFactory.create();
            user = manager.get(email);
            if(isLogIn(user)) return user;

        }catch (NullPointerException exception){
            LogHelper.createNewErrorLog(exception.getMessage());
        }
        return user;
    }

}

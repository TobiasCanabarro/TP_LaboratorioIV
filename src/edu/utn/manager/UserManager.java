package edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.enums.LogInResult;
import edu.utn.log.LogHelper;
import edu.utn.mail.Mail;
import edu.utn.mapper.UserMapper;
import edu.utn.validator.UserValidator;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class UserManager implements Manager <User> {

    private UserValidator validator;
    private UserMapper mapper;

    public UserManager (UserMapper mapper, UserValidator validator){
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public boolean save (User user) {
      if(!validator.isValidUser(user)){
          return false;
      }
        return mapper.save(user);
    }

    @Override
    public boolean update (User user){
        return mapper.update(user);
    }

    @Override
    public boolean delete(User user) {
        return mapper.delete(user);
    }

    public User get(String email) {
        return mapper.get(email);
    }

    public User get (long id) {
        return mapper.get(id);
    }

    public boolean signIn (User user) {
        boolean value = validator.isValidUser(user);
        value &= !validator.existsUser(user.getEmail());
        if(value) {
            try {
                value = save(user);
            }catch (Exception ex){
                LogHelper.createNewErrorLog(ex.getMessage());
            }
        }
        return value;
    }

    public LogInResult logIn (String email, String password) throws MessagingException {
        User user = get(email);

        if (!validator.existsUser(email)) {
            return LogInResult.ERR_USER_DOES_NOT_EXIST;
        }
        if (user.isLogIn()) {
            return LogInResult.ERR_USER_IS_ALREADY_LOGGED_IN;
        }
        if(user.isLocked()){
            return LogInResult.ERR_IS_LOCKED;
        }
        boolean value = user.getPassword().equals(password);
        if(value){
            user.setLogIn(true);
            LogHelper.createNewDebugLog("Se inicia sesion ");
        }
        else {
            user.setAttemptLogin(user.getAttemptLogin() + 1);
            value &= validator.attemptsRemain(user);
        }
        value &= update(user);
        return value ? LogInResult.OK : LogInResult.ERR_AUTHENTICATION;
    }

    public boolean logOut(String email){
        User user = get(email);
        user.setLogIn(false);
        return update(user);
    }

    public boolean changePassword(String email, String newPassword){
        User user = get(email);
        user.setPassword(newPassword);
        return update(user);
    }

    public boolean requestUnlockedAccount (String email, String endpoint) {
        User user = get(email);
        boolean value = validator.isLocked(user);
        if(value){
            try{
                Mail.sendMail(email, LogInResult.UNLOCKED_ACCOUNT, "Ingrese a esta ruta para desbloquear su cuenta " + endpoint);
                LogHelper.createNewDebugLog("Se envia email al usuario " + email);
            }catch (MessagingException exception){
                LogHelper.createNewErrorLog(exception.getMessage());
            }
        }
        return value;
    }

    public UserValidator getValidator() {
        return validator;
    }

    public void setValidator(UserValidator validator) {
        this.validator = validator;
    }

    public UserMapper getMapper() {
        return mapper;
    }

    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

}

//errores de login
//1 = pass incorrecota
//2 = se bloquea
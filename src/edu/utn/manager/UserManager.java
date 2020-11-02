package edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.enums.Result;
import edu.utn.helper.EncryptHelper;
import edu.utn.log.LogHelper;
import edu.utn.mail.Mail;
import edu.utn.mapper.UserMapper;
import edu.utn.validator.UserValidator;

import javax.mail.MessagingException;
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

    @Override
    public User get (long id) {
        return mapper.get(id);
    }

    public boolean signIn (User user) {
        boolean value = validator.isValidUser(user);
        user.setPassword(EncryptHelper.encryptPassword(user.getPassword()));
        value &= !validator.existsUser(user.getEmail());
        if(value) {
            try {
                value = save(user);
            }catch (Exception ex){
                LogHelper.createNewErrorLog(ex.getMessage());
            }
        }
        if(value){
            LogHelper.createNewDebugLog(Result.SIGN_IN_OK);
        }
        return value;
    }

    public Result logIn (String email, String password) throws MessagingException {
        User user = get(email);

        if (!validator.existsUser(email)) {
            return Result.ERR_USER_DOES_NOT_EXIST;
        }
        if (user.isLogIn()) {
            return Result.ERR_USER_IS_ALREADY_LOGGED_IN;
        }
        if(user.isLocked()){
            return Result.ERR_IS_LOCKED;
        }
        password = EncryptHelper.encryptPassword(password);
        boolean value = user.getPassword().equals(password);
        if(value){
            user.setAttemptLogin(0);
            user.setLogIn(true);
        }
        else {
            user.setAttemptLogin(user.getAttemptLogin() + 1);
            value &= validator.attemptsRemain(user);
        }
        value &= update(user);
        if(value){
            LogHelper.createNewDebugLog(Result.LOG_IN_OK);
        }
        return value ? Result.LOG_IN_OK : Result.ERR_AUTHENTICATION;
    }

    public boolean logOut(long id){
        User user = get(id);
        user.setLogIn(false);
        boolean value = update(user);
        LogHelper.createNewDebugLog(Result.LOG_OUT_OK);
        return value;
    }

    public boolean changePassword(long id, String newPassword){
        User user = get(id);
        user.setPassword(newPassword);
        LogHelper.createNewDebugLog(Result.CHANGE_PASSWORD);
        return update(user);
    }

    public boolean requestUnlockedAccount (String email, String endpoint) {
        User user = get(email);
        boolean value = validator.isLocked(user);
        if(value){
            try{
                Mail.sendMail(email, Result.UNLOCKED_ACCOUNT, "Ingrese a esta ruta para desbloquear su cuenta " + endpoint);
                LogHelper.createNewDebugLog(Result.UNLOCKED_ACCOUNT);
            }catch (MessagingException exception){
                LogHelper.createNewErrorLog(exception.getMessage());
            }
        }
        return value;
    }

    public List<User> getAllUser () {
        return mapper.getAllUsers();
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

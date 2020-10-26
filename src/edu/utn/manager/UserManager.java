package edu.utn.manager;

import edu.utn.entity.ChangePassword;
import edu.utn.entity.User;
import edu.utn.exception.EmailException;
import edu.utn.exception.NameException;
import edu.utn.exception.PasswordException;
import edu.utn.exception.SurNameException;
import edu.utn.factory.UserManagerFactory;
import edu.utn.log.LogHelper;
import edu.utn.mapper.UserMapper;
import edu.utn.validator.UserValidator;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserManager implements Manager {

    private UserValidator validator;
    private UserMapper mapper;

    private static final String UPDATE_USER_ATTEMPT_LOGIN = "UPDATE lab.user set attempt_login = ? WHERE email = ?";
    private static final String UPDATE_USER_LOG_IN = "UPDATE lab.user set log_in = ? WHERE email = ?";
    private static final String UPDATE_USER_LOCKED = "UPDATE lab.user set locked = ? WHERE email = ?";

    public UserManager (UserMapper mapper, UserValidator validator){
        this.mapper = mapper;
        this.validator = validator;
    }

    public boolean save (User user) {
        boolean value = validator.isValidUser(user);
        value &= mapper.save(user);
        return value;
    }

    public User get (String email) {
        return mapper.get(email);
    }

    public boolean update (String query, Map<Integer, Object> parameters){
        return mapper.update(query, parameters);
    }

    public boolean updateLogIn (String email, boolean logIn) {
        boolean value = validator.alreadyLoggedIn(email);
        int i =0;
        if(!value){
            Map<Integer, Object> parameters = new HashMap<>();
            parameters.put(++i, logIn);
            parameters.put(++i, email);
            value &= update(UPDATE_USER_LOG_IN, parameters);
        }
        return value;
    }

    @Override
    public boolean updateAttempt(String email, int attempt) {
        Map<Integer, Object> parameters = new HashMap<>();
        int i = 0;
        parameters.put(++i, attempt);
        parameters.put(++i, email);
        return  update(UPDATE_USER_ATTEMPT_LOGIN, parameters);
    }

    @Override
    public boolean updateLocked(String email, boolean locked) {
        Map<Integer, Object> parameters = new HashMap<>();
        int i = 0;
        parameters.put(++i, locked);
        parameters.put(++i, email);
        return  update(UPDATE_USER_LOCKED, parameters);
    }

    @Override
    public User get(String email, String password) {
        User user = get(email);
        return user;
    }

    public boolean signIn (User user) {
        boolean value = validator.existsUser(user.getEmail());
        value &= validator.isValidUser(user);
        boolean success = false;
        if(!value) {
            try {
                success = save(user);
            }catch (Exception ex){
                LogHelper.createNewLog(ex.getMessage());
            }
        }
        return success;
    }

    public boolean logIn (String email, String password)  {
        boolean value = validator.isValidPassword(password);
        value &= updateLogIn(email, true);
        return value;
    }

//    public boolean changePassword (User user, String newPassword) throws PasswordException {
//        ChangePassword changePassword = new ChangePassword();
//        return changePassword.changePassword(user, newPassword);
//    }

//    public boolean logOut () {
//        LogInManager logInManager = new LogInManager();
//        return  logInManager.logOut(getUser());
//    }


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

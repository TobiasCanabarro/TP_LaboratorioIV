package edu.utn.manager;

import edu.utn.entity.ChangePassword;
import edu.utn.entity.LogIn;
import edu.utn.entity.SignIn;
import edu.utn.entity.User;
import edu.utn.exception.EmailException;
import edu.utn.exception.NameException;
import edu.utn.exception.PasswordException;
import edu.utn.exception.SurNameException;
import edu.utn.log.LogHelper;
import edu.utn.mapper.UserMapper;
import edu.utn.validator.UserValidator;
import java.sql.SQLException;

public class UserManager implements Manager {

    private UserMapper userMapper;

    public UserManager (UserMapper userMapper){
        setUserMapper(userMapper);
    }

    public boolean save () {
        UserValidator validator = new UserValidator();
        boolean success = false;
        try {
            validator.isValid(getUser());
            success = userMapper.save();
        }catch (EmailException ex){
                LogHelper.setNewLog(ex.getMessage());
        }catch (NameException ex){
            LogHelper.setNewLog(ex.getMessage());
        }catch (SurNameException ex){
            LogHelper.setNewLog(ex.getMessage());
        }catch (PasswordException ex){
            LogHelper.setNewLog(ex.getMessage());
        }
        catch (Exception ex){
            LogHelper.setNewLog(ex.getMessage());
        }
        finally {
            return success;
        }
    }

    public User get () {
        return getUserMapper().get(getUser().getEmail());
    }

    public boolean update (){
        boolean value = false;
        try {
            value = getUserMapper().update();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return value;
    }

    public boolean changePassword (User user, String newPassword) throws PasswordException {
        ChangePassword changePassword = new ChangePassword();
        return changePassword.changePassword(user, newPassword);
    }

    public boolean signIn () {
        SignIn signIn = new SignIn();
        return signIn.signIn(getUser());
    }

    public boolean logIn () {
        LogIn logIn = new LogIn();
        return logIn.logIn(getUser());
    }

    public boolean logOut () {
        LogIn logIn = new LogIn();
        return  logIn.logOut(getUser());
    }

    public User getUser() {
        return getUserMapper().getUser();
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}

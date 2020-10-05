package edu.utn.manager;

import edu.utn.entity.LogIn;
import edu.utn.entity.User;
import edu.utn.exception.EmailException;
import edu.utn.exception.NameException;
import edu.utn.exception.SurNameException;
import edu.utn.mapper.UserMapper;
import edu.utn.validator.UserValidator;

import java.sql.SQLException;

public class UserManager {

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
            System.out.println(ex.getMessage());
        }catch (NameException ex){
            System.out.println(ex.getMessage());
        }catch (SurNameException ex){
            System.out.println(ex.getMessage());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
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

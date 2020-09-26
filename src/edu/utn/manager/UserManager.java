package edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.exception.EmailException;
import edu.utn.exception.NameException;
import edu.utn.exception.SurNameException;
import edu.utn.mapper.UserMapper;
import edu.utn.validator.UserValidator;

public class UserManager {

    private UserMapper userMapper;

    public UserManager (UserMapper userMapper){
        setUserMapper(userMapper);
    }

    public boolean save (User user) {
        UserValidator validator = new UserValidator();
        boolean success = false;
        try {
            validator.isValid(user);
            success = userMapper.save(user);
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


    public User get (String id) {
        return getUserMapper().get(id);
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}

package edu.utn.manager;

import edu.utn.entity.User;
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

        }catch (Exception ex){
            System.out.println("EXPLOTOOO! xd");
        }finally {
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

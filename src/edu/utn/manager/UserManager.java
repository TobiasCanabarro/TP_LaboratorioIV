package edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.mapper.UserMapper;

public class UserManager {

    private UserMapper userMapper;

    public UserManager (UserMapper userMapper){
        setUserMapper(userMapper);
    }

    public boolean save (User user) {
        //valitor ->
        boolean success = false;
        try {
            success = userMapper.save(user);

        }catch (Exception ex){
            System.out.println("EXPLOTOOO! xd");
        }finally {
            return success;
        }
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}

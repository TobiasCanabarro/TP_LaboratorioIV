package edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.log.LogHelper;
import edu.utn.mapper.UserMapper;
import edu.utn.validator.UserValidator;

public class UserManager implements Manager {

    private UserValidator validator;
    private UserMapper mapper;

    public UserManager (UserMapper mapper, UserValidator validator){
        this.mapper = mapper;
        this.validator = validator;
    }

    public boolean save (User user) {
      if(!validator.isValidUser(user))
          return false;

        return mapper.save(user);
    }

    public User get (String email) {
        return mapper.get(email);
    }

    public boolean update (User user){
        return mapper.update(user);
    }


    @Override
    public User get(String email, String password) {
        User user = get(email);
        return user;
    }


    public boolean signIn (User user) {
        boolean value = validator.isValidUser(user);
        value &= !validator.existsUser(user.getEmail());

        if(value) {
            try {
                value = save(user);
            }catch (Exception ex){
                LogHelper.createNewLog(ex.getMessage());
            }
        }
        return value;
    }

    public boolean logIn (String email, String password)  {
        User user = get(email);

        if(!validator.existsUser(email)){
            return false;
        }
        if(user.isLogIn()){
            return false;
        }
        if(!validator.passCorrect(user.getPassword(), password)){
            if(user.getAttemptLogin() <= 4 ){
                user.setAttemptLogin(user.getAttemptLogin()+1);
            }else{
                user.setAttemptLogin(user.getAttemptLogin()+1);
                user.setLocked(true);
            }
            update(user);
            return false;
        }
        else{
            user.setLogIn(true);
        }
        return update(user);
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
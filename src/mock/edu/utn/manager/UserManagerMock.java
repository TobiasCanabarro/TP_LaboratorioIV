package mock.edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.manager.Manager;
import mock.edu.utn.mapper.UserMapperMock;
import mock.edu.utn.validator.UserValidatorMock;

public class UserManagerMock implements Manager <User> {

    private UserValidatorMock validatorMock;
    private UserMapperMock mapperMock;

    public UserManagerMock(UserMapperMock mapperMock, UserValidatorMock validatorMock) {
        setMapperMock(mapperMock);
        setValidatorMock(validatorMock);
    }

    @Override
    public boolean save(User user) {
        boolean value = validatorMock.isValid();
        getMapperMock().setValid(value);
        if(validatorMock.isValid()){
            value = mapperMock.save(user);
        }
        return value;
    }

    @Override
    public boolean update(User user) {
        boolean value = validatorMock.isValid();
        getMapperMock().setValid(value);
        if(value){
            value = mapperMock.update(user);
        }
        return value;
    }


    @Override
    public boolean delete(User user) {
        boolean value = validatorMock.isValid();
        getMapperMock().setValid(value);
        if(value){
            value = mapperMock.delete(user);
        }
        return value;
    }

    @Override
    public User get(long id) {
        return null;
    }

    public User get(String email) {
        boolean value = validatorMock.isValid();
        getMapperMock().setValid(value);
        User user = null;
        if(value){
            user = mapperMock.get(email);
        }
        return user;
    }

    public UserMapperMock getMapperMock() {
        return mapperMock;
    }

    public void setMapperMock(UserMapperMock mapperMock) {
        this.mapperMock = mapperMock;
    }

    public void setValidMapper (boolean valid) {
        getMapperMock().setValid(valid);
    }

    public UserValidatorMock getValidatorMock() {
        return validatorMock;
    }

    public void setValidatorMock(UserValidatorMock validatorMock) {
        this.validatorMock = validatorMock;
    }

}

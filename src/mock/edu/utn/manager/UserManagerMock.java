package mock.edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.manager.Manager;

import mock.edu.utn.mapper.UserMapperMock;
import mock.edu.utn.validator.UserValidatorMock;

import java.sql.Date;

public class UserManagerMock implements Manager {

    private UserValidatorMock validatorMock;
    private UserMapperMock mapperMock;

    public UserManagerMock(UserMapperMock mapperMock, UserValidatorMock validatorMock) {
        setMapperMock(mapperMock);
        setValidatorMock(validatorMock);
    }

    @Override
    public boolean save(User user) {
        return validatorMock.isValid();
    }

    @Override
    public boolean update(User user) {
        return validatorMock.isValid();
    }

    @Override
    public User get(String email) {
        return validatorMock.isValid() ? new User("John", "john123", "Doe", "john@gmail.com", "jonh", new Date(9999)) : null;
    }


    public User get (){
        return getMapperMock().get();
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

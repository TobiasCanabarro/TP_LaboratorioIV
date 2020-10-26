package mock.edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.manager.Manager;
import mock.edu.utn.mapper.UserMapperMock;

public class UserManagerMock implements Manager {

    private UserMapperMock mapperMock;

    public UserManagerMock(UserMapperMock mapperMock) {
        setMapperMock(mapperMock);
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

    @Override
    public boolean save(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User get(String email, String password) {
        return null;
    }
}

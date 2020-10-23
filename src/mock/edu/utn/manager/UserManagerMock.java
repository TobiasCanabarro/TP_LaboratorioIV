package mock.edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.manager.Manager;
import mock.edu.utn.mapper.UserMapperMock;

public class UserManagerMock implements Manager {

    private UserMapperMock mapperMock;

    public UserManagerMock(UserMapperMock mapperMock) {
        setMapperMock(mapperMock);
    }

    @Override
    public boolean save() {
        return getMapperMock().save();
    }

    @Override
    public boolean update() {
        return getMapperMock().update();
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

}

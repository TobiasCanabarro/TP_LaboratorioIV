package mock.edu.utn.manager;

import edu.utn.manager.Manager;
import mock.edu.utn.mapper.UserLogMapperMock;
import mock.edu.utn.mapper.UserMapperMock;

public class UserLogManagerMock implements Manager {

    private UserLogMapperMock mapperMock;
    private boolean isValid;

    public UserLogManagerMock(UserLogMapperMock mapperMock) {
        setMapperMock(mapperMock);
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public UserLogMapperMock getMapperMock() {
        return mapperMock;
    }

    public void setMapperMock(UserLogMapperMock mapperMock) {
        this.mapperMock = mapperMock;
    }
}

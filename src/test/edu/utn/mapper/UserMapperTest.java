package test.edu.utn.mapper;

import edu.utn.entity.User;
import edu.utn.mapper.UserMapper;
import mock.edu.utn.mapper.UserMapperMock;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    private static final User USER = new User("tobias", "tobias123", "canabarro", "tobias@gmail.com", "tobi", new Date(99999));

    @Test
    void save() {

        UserMapperMock mapperMock = new UserMapperMock();
        mapperMock.setValid(true);
        boolean value = mapperMock.save(USER);

        assertEquals(true, value);
    }

    @Test
    void saveFail() {

        UserMapperMock mapperMock = new UserMapperMock();
        mapperMock.setValid(false);
        boolean value = mapperMock.save(USER);

        assertEquals(false, value);
    }

    @Test
    void update() {

        UserMapperMock mapperMock = new UserMapperMock();
        mapperMock.setValid(true);

        boolean value = mapperMock.update(USER);

        assertEquals(true, value);
    }

    @Test
    void updateFail() {

        UserMapperMock mapperMock = new UserMapperMock();
        mapperMock.setValid(false);

        boolean value = mapperMock.update(USER);

        assertEquals(false, value);
    }

    @Test
    void delete() {

        UserMapperMock mapperMock = new UserMapperMock();
        mapperMock.setValid(true);

        boolean value = mapperMock.delete(USER);

        assertEquals(true, value);

    }

    @Test
    void deleteFail() {

        UserMapperMock mapperMock = new UserMapperMock();
        mapperMock.setValid(false);

        boolean value = mapperMock.delete(USER);

        assertEquals(false, value);
    }

    @Test
    void get() {

        UserMapperMock mapperMock = new UserMapperMock();
        mapperMock.setValid(true);

        User foundUser = mapperMock.get(USER.getEmail());
        boolean value = foundUser != null;

        assertEquals(true, value);
    }

    @Test
    void getFail() {

        UserMapperMock mapperMock = new UserMapperMock();
        mapperMock.setValid(false);

        User foundUser = mapperMock.get(USER.getEmail());
        boolean value = foundUser != null;

        assertEquals(false, value);
    }

    @Test
    void getAllUsers() {

        UserMapperMock mapperMock = new UserMapperMock();
        mapperMock.setValid(true);

        List<User> users = mapperMock.getAllUser();
        boolean value = users != null;

        assertEquals(true, value);
    }

    @Test
    void getAllUsersFail() {

        UserMapperMock mapperMock = new UserMapperMock();
        mapperMock.setValid(false);

        List<User> users = mapperMock.getAllUser();
        boolean value = users != null;

        assertEquals(false, value);
    }

}
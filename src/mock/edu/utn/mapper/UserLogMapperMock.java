package mock.edu.utn.mapper;

import edu.utn.entity.UserLog;
import edu.utn.mapper.Mapper;

import java.sql.SQLException;

public class UserLogMapperMock implements Mapper {

    private UserLog userLog;
    private boolean isValid;

    public UserLogMapperMock(UserLog userLog) {
        setUserLog(userLog);
    }

    @Override
    public boolean save() throws SQLException {
        return false;
    }

    @Override
    public boolean update() throws SQLException {
        return false;
    }

    public UserLog getUserLog() {
        return userLog;
    }

    public void setUserLog(UserLog userLog) {
        this.userLog = userLog;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}

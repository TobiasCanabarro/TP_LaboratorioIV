package mock.edu.utn.mapper;

import edu.utn.entity.User;
import edu.utn.mapper.Mapper;

import java.sql.Date;
import java.util.Map;

public class UserMapperMock implements Mapper <User> {

    private boolean isValid;

    @Override
    public boolean save(User user){
        return isValid();
    }

    @Override
    public boolean update(User user){
        return isValid();
    }

    @Override
    public boolean delete(User user) {
        return isValid();
    }

    public User get(String email){
        if(isValid()){
            return  new User("John", "john123", "Doe", "john@gmail.com", "jonh", new Date(9999));
        }
        else {
            return null;
        }
    }

    //Este metodo en esta clase no hace nada :D
    @Override
    public Map<Integer, Object> createParameters(User object) {
        return null;
    }

    @Override
    public User getEntityRecord(Map<String, Object> record) {
        return null;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }


}

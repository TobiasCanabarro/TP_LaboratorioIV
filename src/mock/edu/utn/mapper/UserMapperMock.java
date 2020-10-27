package mock.edu.utn.mapper;

import edu.utn.entity.User;
import edu.utn.mapper.Mapper;

import java.sql.Date;
import java.util.Map;

public class UserMapperMock <T extends User> implements Mapper <T> {

    private boolean isValid;

    @Override
    public boolean save(User user){
        return isValid();
    }

    @Override
    public boolean update(User user){
        return isValid();
    }

    public User get(){
        if(isValid()){
            return new User("Pedro", "pedro123", "Perez", "pedro@gmail.com", "Pedrito", new Date(9999));
        }
        else {
            return null;
        }
    }

    @Override
    public Map<Integer, Object> createParameters(T object) {
        return null;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }


}

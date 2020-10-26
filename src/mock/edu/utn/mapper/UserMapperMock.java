package mock.edu.utn.mapper;

import edu.utn.entity.User;
import edu.utn.mapper.Mapper;

import java.sql.Date;

public class UserMapperMock implements Mapper {

    private boolean isValid;

    @Override
    public boolean save(){
        return isValid();
    }

    @Override
    public boolean update(){
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

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}

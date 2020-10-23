package mock.edu.utn.mapper;

import edu.utn.entity.User;
import edu.utn.mapper.Mapper;

public class UserMapperMock implements Mapper {

    private User user;
    private boolean isValid;

    public UserMapperMock(User user) {
        setUser(user);
    }

    @Override
    public boolean save(){
        return isValid();
    }

    @Override
    public boolean update(){
        return isValid();
    }

    public User get(){
        if(!isValid()){
            return null;
        }
        return getUser();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}

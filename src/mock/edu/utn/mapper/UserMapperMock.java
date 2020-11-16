package mock.edu.utn.mapper;

import edu.utn.entity.User;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.UserManager;
import edu.utn.mapper.Mapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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

    public List<User> getAllUser (){
        List<User> users = null;

        if(isValid()){
            users = new ArrayList<>();
            users.add(new User("John", "john123", "Doe", "john@gmail.com", "jonh", new Date(9999)));
            users.add(new User("Jane", "jane123", "Doe", "jane@gmail.com", "jane", new Date(8888)));
        }
        return users;
    }

    //Este metodo en esta clase no hace nada :D
    @Override
    public Map<Integer, Object> createParameters(User user) {
        UserManager manager = UserManagerFactory.create();
        return manager.getMapper().createParameters(user);
    }

    public Map<Integer, Object> createParametersForUpdate (User user, String email) {
        Map<Integer, Object> parameters = createParameters(user);
        int i = parameters.size();
        parameters.put(++i, user.getAttemptLogin());
        parameters.put(++i, user.isLogIn());
        parameters.put(++i, user.isLocked());
        parameters.put(++i, email);
        return parameters;
    }


    @Override
    public User getEntityRecord(Map<String, Object> record) {
        UserManager manager = UserManagerFactory.create();
        return manager.getMapper().getEntityRecord(record);
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }


}

package mock.edu.utn.mapper;

import edu.utn.entity.UserPost;
import edu.utn.factory.UserPostManagerFactory;
import edu.utn.manager.PostManager;
import edu.utn.mapper.Mapper;

import java.sql.Date;
import java.util.Map;

public class PostMapperMock implements Mapper <UserPost> {

    private boolean valid;

    @Override
    public boolean save(UserPost object) {
        return isValid();
    }

    @Override
    public boolean update(UserPost object) {
        return isValid();
    }

    @Override
    public boolean delete(UserPost object) {
        return isValid();
    }

    public UserPost get (long id){
        return isValid()?new UserPost(1, "Hola Mundo!", new Date(9999)):null;
    }

    @Override
    public Map<Integer, Object> createParameters(UserPost post) {
        PostManager manager = UserPostManagerFactory.create();
        return manager.getMapper().createParameters(post);
    }

    //Este metodo no se usa en esta clase
    @Override
    public UserPost getEntityRecord(Map<String, Object> record) {
        return null;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}

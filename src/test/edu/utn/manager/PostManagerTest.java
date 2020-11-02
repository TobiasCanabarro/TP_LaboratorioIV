package test.edu.utn.manager;

import edu.utn.entity.UserPost;
import mock.edu.utn.factory.PostManagerFactoryMock;
import mock.edu.utn.manager.PostManagerMock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.util.List;

public class PostManagerTest {

    @Test
    void saveOk(){
        UserPost post = new UserPost(14, "Hola Mundo!", new Date(999));
        PostManagerMock manager = PostManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        boolean value = manager.save(post);
        assertEquals(true, value);
    }

    @Test
    void saveFail(){
        UserPost post = new UserPost(14, "Hola Mundo!", new Date(999));
        PostManagerMock manager = PostManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        boolean value = manager.save(post);
        assertEquals(false, value);
    }

    @Test
    void updateOk (){
        UserPost post = new UserPost(14, "Hola Mundo!", new Date(999));
        PostManagerMock manager = PostManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        boolean value = manager.update(post);
        assertEquals(true, value);
    }

    @Test
    void updateFail (){
        UserPost post = new UserPost(14, "Hola Mundo!", new Date(999));
        PostManagerMock manager = PostManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        boolean value = manager.update(post);
        assertEquals(false, value);
    }

    @Test
    void getOk (){
        PostManagerMock manager = PostManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        UserPost post = manager.get(14);
        boolean value = post != null;
        assertEquals(true, value);
    }

    @Test
    void getFail (){
        PostManagerMock manager = PostManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        UserPost post = manager.get(14);
        boolean value = post != null;
        assertEquals(false, value);
    }

    @Test
    void newPostOk (){
        UserPost post = new UserPost(14, "Hola Mundo!", new Date(999));
        PostManagerMock manager = PostManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        boolean value = manager.getValidator().isValid();
        if(value){
            value &= manager.save(post);
        }
        assertEquals(true, value);
    }

    @Test
    void newPostFail (){
        UserPost post = new UserPost(14, "Hola Mundo!", new Date(999));
        PostManagerMock manager = PostManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        boolean value = manager.getValidator().isValid();
        if(value){
            value &= manager.save(post);
        }
        assertEquals(false, value);
    }

    @Test
    void myPostsOk (){
        PostManagerMock manager = PostManagerFactoryMock.create();
        manager.getValidator().setValid(true);
        List<UserPost> posts = manager.myPosts(14);
        boolean value = posts != null;
        assertEquals(true, value);
    }

    @Test
    void myPostsFail (){
        PostManagerMock manager = PostManagerFactoryMock.create();
        manager.getValidator().setValid(false);
        List<UserPost> posts = manager.myPosts(14);
        boolean value = posts != null;
        assertEquals(false, value);
    }
}

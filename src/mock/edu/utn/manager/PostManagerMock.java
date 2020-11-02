package mock.edu.utn.manager;

import edu.utn.entity.UserPost;
import edu.utn.manager.Manager;
import mock.edu.utn.mapper.PostMapperMock;
import mock.edu.utn.validator.PostValidatorMock;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PostManagerMock implements Manager <UserPost> {

    private PostValidatorMock validator;
    private PostMapperMock mapper;

    public PostManagerMock(PostMapperMock mapper, PostValidatorMock validator) {
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public boolean save(UserPost post) {
        boolean value = validator.isValid();
        mapper.setValid(value);
        if(value){
            value = mapper.save(post);
        }
        return value;
    }

    @Override
    public boolean update(UserPost post) {
        boolean value = validator.isValid();
        mapper.setValid(value);
        if(value){
            value = mapper.update(post);
        }
        return value;
    }

    @Override
    public boolean delete(UserPost post) {
        boolean value = validator.isValid();
        mapper.setValid(value);
        if(value){
            value = mapper.delete(post);
        }
        return value;
    }

    @Override
    public UserPost get(long id) {
        UserPost post = null;
        boolean value = validator.isValid();
        mapper.setValid(value);
        if(value){
            post = mapper.get(id);
        }
        return post;
    }

    public boolean newPost (String post, long id){
        UserPost userPost = new UserPost(id, post, new Date(9999));
        return save(userPost);
    }

    public List<UserPost> myPosts (long id){
        List<UserPost> posts = null;
        boolean value = validator.isValid();
        mapper.setValid(value);
        if(value){
            posts = new ArrayList<>();
            posts.add(mapper.get(id));
        }
        return posts;
    }


    public PostValidatorMock getValidator() {
        return validator;
    }

    public void setValidator(PostValidatorMock validator) {
        this.validator = validator;
    }

    public PostMapperMock getMapper() {
        return mapper;
    }

    public void setMapper(PostMapperMock mapper) {
        this.mapper = mapper;
    }
}

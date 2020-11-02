package edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.entity.UserPost;
import edu.utn.factory.RequestRelationshipManagerFactory;
import edu.utn.helper.DateHelper;
import edu.utn.mapper.PostMapper;
import edu.utn.validator.PostValidator;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostManager implements Manager <UserPost>{

    private PostValidator validator;
    private PostMapper mapper;

    public PostManager(PostMapper mapper, PostValidator validator) {
        setValidator(validator);
        setMapper(mapper);
    }

    @Override
    public boolean save(UserPost post) {
        return mapper.save(post);
    }

    @Override
    public boolean update(UserPost post) {
        return mapper.update(post);
    }

    @Override
    public boolean delete(UserPost post) {
        return mapper.delete(post);
    }

    @Override
    public UserPost get(long id) {
        return null;
    }

    public List<Map<String, Object>> getAll (long id){
        return mapper.get(id);
    }

    public boolean newPost (String post, long idUser){
        UserPost userPost = new UserPost(idUser, post, DateHelper.currentDate());
        return save(userPost);
    }

    public List<UserPost> myPosts(long id){
        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
        List<User> myFriends = manager.myFriends(id);
        List<UserPost> posts = new ArrayList<>();

        List<Map<String, Object>> myPosts = getAll(id);

        //Guarda todas las publicaciones del usuario
        for(Map<String, Object> result : myPosts){
            posts.add(getPostResult(result));
        }

        //Guarda todas las publicaciones de los amigos
        for(User friend : myFriends){
            List<Map<String, Object>>  friendPosts = getAll(friend.getId());
            for (Map<String, Object> post : friendPosts){
                posts.add(getPostResult(post));
            }
        }

        return posts;//retorna la lista con todas las publicaciones del usuario como de los amigos
    }

    private UserPost getPostResult (Map<String, Object> results){
        return new UserPost((long)results.get("id_publication"), (long)results.get("id_user"),
                results.get("post").toString(), (Date)results.get("date_publication"));
    }


    public PostValidator getValidator() {
        return validator;
    }

    public void setValidator(PostValidator validator) {
        this.validator = validator;
    }

    public PostMapper getMapper() {
        return mapper;
    }

    public void setMapper(PostMapper mapper) {
        this.mapper = mapper;
    }
}

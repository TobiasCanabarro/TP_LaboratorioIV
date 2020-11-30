package edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.entity.UserPost;
import edu.utn.factory.RequestRelationshipManagerFactory;
import edu.utn.helper.DateHelper;
import edu.utn.log.LogHelper;
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

        boolean value = false;

        try {
            value = mapper.save(post);
        }catch (Exception exception){
            LogHelper.createNewErrorLog(exception.getMessage());
        }

        return value;
    }

    @Override
    public boolean update(UserPost post) {

        boolean value = false;

        try {
            value = mapper.update(post);
        }catch (Exception exception){
            LogHelper.createNewErrorLog(exception.getMessage());
        }

        return value;
    }

    //Este metodo en esta clase no se usa.
    @Override
    public boolean delete(UserPost post) {

        boolean value = false;

        try {
            value = mapper.delete(post);
        }catch (Exception exception){
            LogHelper.createNewErrorLog(exception.getMessage());
        }

        return value;
    }

    //Este metodo no se usa en esta clase.
    @Override
    public UserPost get(long id) {
        return null;
    }

    //Trae todas las publicaciones del usuario como de los amigos.
    public List<Map<String, Object>> getAll (long id){

        List<Map<String, Object>> list = null;

        try {
            list = mapper.get(id);
        }catch (Exception exception){
            LogHelper.createNewErrorLog(exception.getMessage());
        }

        return list;
    }

    public boolean newPost (String post, long idUser){
        if(!validator.IsValidLength(post)){
            return false;
        }
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

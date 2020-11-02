package edu.utn.factory;

import edu.utn.manager.PostManager;
import edu.utn.mapper.PostMapper;
import edu.utn.validator.PostValidator;

public class UserPostManagerFactory implements Factory{

    public static PostManager create (){
        return new PostManager(new PostMapper(), new PostValidator());
    }
}

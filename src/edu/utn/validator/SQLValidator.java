package edu.utn.validator;

import edu.utn.entity.User;
import edu.utn.manager.UserManager;

import java.util.Map;

public class SQLValidator {

   public  boolean existsUser (UserManager manager, User user) {
       User found = manager.get(); //key para buscar el registro. Esta hardcodeado el email para la busqueda
       if (found != null) {
           return found.getEmail().equals(user.getEmail());
       }
       return false;
   }

   public long publicationIdIsNull (Map<String, Object> record, String publicationId) {
       if(record.get(publicationId) == null){
            return 0;
       }else {
           return (long)record.get(publicationId);
       }
   }
}

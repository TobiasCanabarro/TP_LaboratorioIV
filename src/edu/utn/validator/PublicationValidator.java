package edu.utn.validator;

import java.util.Map;

public class PublicationValidator {

    //En caso que el usuario no tenga una publicacion se retorna un 0
   public long publicationIdIsNull (Map<String, Object> record, String publicationId) {
       if(record.get(publicationId) == null){
            return 0;
       }else {
           return (long)record.get(publicationId);
       }
   }
}

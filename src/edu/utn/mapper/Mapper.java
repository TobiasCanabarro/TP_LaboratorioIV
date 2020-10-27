package edu.utn.mapper;

import java.util.Map;

public interface Mapper <T> {

    boolean save (T object);
    
    boolean update(T object);
    
    Map<Integer, Object> createParameters(T object);
}

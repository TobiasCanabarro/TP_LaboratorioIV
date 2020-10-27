package edu.utn.dao;

import java.util.List;
import java.util.Map;

public interface Dao {

    int save(Map<Integer, Object> parameters);

    int update (Map<Integer, Object> parameters);

    List<Map<String, Object>> get(Map<Integer, Object> parameters);

    int delete(Map<Integer, Object> parameters);
}

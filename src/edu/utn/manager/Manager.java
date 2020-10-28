package edu.utn.manager;

public interface Manager <T> {

//      boolean save(User user);
//
//      boolean update(User user);
//
//      User get(String email);

          boolean save(T object);

          boolean update(T object);

          boolean delete(T object);

}

package edu.utn.manager;

public interface Manager <T> {

    boolean save(T object);

    boolean update(T object);

    boolean delete(T object);

    T get (long id);

}

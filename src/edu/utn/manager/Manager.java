package edu.utn.manager;


import edu.utn.entity.User;

public interface Manager{

      boolean save(User user);

      boolean update(User user);

      User get(String email);

}

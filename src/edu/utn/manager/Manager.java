package edu.utn.manager;


import edu.utn.entity.User;

public interface Manager{

      boolean save(User user);

      boolean updateLogIn(String email, boolean logIn);

      boolean updateAttempt(String email, int attempt);

      boolean updateLocked(String email, boolean locked);

      User get(String email, String password);

}

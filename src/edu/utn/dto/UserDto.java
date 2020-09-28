package edu.utn.dto;

import edu.utn.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserDto {

    public Map<Integer, Object> saveUserOnMapper (User user) {
        int i = 1;
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(i++, user.getName());
        parameters.put(i++, user.getPassword());
        parameters.put(i++, user.getSurname());
        parameters.put(i++, user.getEmail());
        parameters.put(i++, user.getNickname());
        parameters.put(i++, user.getBirthday());
        return parameters;
    }
}

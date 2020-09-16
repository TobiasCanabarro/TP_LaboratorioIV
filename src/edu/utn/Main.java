package edu.utn;

import edu.utn.mapper.UserMapper;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
	// write your code here

        UserMapper mapper = new UserMapper();
        mapper.setUser();
    }
}

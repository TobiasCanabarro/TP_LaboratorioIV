package edu.utn.mapper;

import edu.utn.entity.User;

import java.sql.*;


public class UserMapper {

    String CONN_STRING = "postgres://192.168.33.10:5432";

    String CONN_STRINGs = "jdbc:postgresql://192.168.33.10:5432/cuvl_db";

    String conn_ = "postgres://hansken.db.elephantsql.com(hansken-01):5432//vikwzvlz";

    User user;



    public void setUser() throws SQLException {
        Connection dbConn = DriverManager.getConnection(CONN_STRINGs,"cuvl","cuvl1234");
        String query = "select * from tpdb1.autor";
        Statement stm = dbConn.createStatement();
        ResultSet rs= stm.executeQuery(query);

        while(rs.next()){
            System.out.println(rs.getString("nombre"));
        }



    }
}

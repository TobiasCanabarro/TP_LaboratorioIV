package edu.utn.mapper;

import java.sql.SQLException;

public interface Mapper {

    boolean save () throws SQLException;
    boolean update() throws SQLException;
}

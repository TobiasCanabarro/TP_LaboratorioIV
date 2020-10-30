package edu.utn.helper;

import java.util.Date;

public class DateHelper {

    public static java.sql.Date currentDate (){
        java.util.Date date = new Date();
        return new java.sql.Date(date.getTime());
    }
}

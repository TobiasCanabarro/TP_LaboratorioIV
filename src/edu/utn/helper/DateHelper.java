package edu.utn.helper;

import java.util.Date;

public class DateHelper {

    //Este metodo devulve la fecha actual
    public static java.sql.Date currentDate (){
        Date date = new Date();
        return new java.sql.Date(date.getTime());
    }
}

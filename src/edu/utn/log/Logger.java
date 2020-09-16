package edu.utn.log;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    List<String> listLog;

    public void setLog(String Enum, String msg) {
        if (listLog == null)
            listLog = new ArrayList<>();

        listLog.add("Enum.Info " + msg);
    }
}



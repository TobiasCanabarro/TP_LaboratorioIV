package edu.utn.log;

import edu.utn.enums.TypeLog;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private List<String> listLog;

    public void setLog(TypeLog typeLog, String log) {
        if (listLog == null) listLog = new ArrayList<>();
        listLog.add( typeLog.getType() + log);
    }

    public List<String> getListLog() {
        return listLog;
    }
}



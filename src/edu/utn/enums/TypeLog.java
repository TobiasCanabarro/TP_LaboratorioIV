package edu.utn.enums;

public enum TypeLog {
    ERROR("ERROR"), DEBUG("DEBUG"), INFO("INFO");

    private String type;

    TypeLog(String type) {
        setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

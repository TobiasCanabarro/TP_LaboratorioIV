package edu.utn.entity;

public class RequestRelationship {

    private long id;
    private long idUserReceive;
    private long idUserSend;
    private boolean state;

    public RequestRelationship(long idUserSend, long idUserReceive) {
        setId(id);
        setIdUserReceive(idUserReceive);
        setIdUserSend(idUserSend);
        setState(false);
    }

    public RequestRelationship(long id, long idUserReceive, long idUserSend, boolean state) {
        setId(id);
        setIdUserReceive(idUserReceive);
        setIdUserSend(idUserSend);
        setState(state);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUserReceive() {
        return idUserReceive;
    }

    public void setIdUserReceive(long idUserReceive) {
        this.idUserReceive = idUserReceive;
    }

    public long getIdUserSend() {
        return idUserSend;
    }

    public void setIdUserSend(long idUserSend) {
        this.idUserSend = idUserSend;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}

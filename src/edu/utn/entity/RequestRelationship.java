package edu.utn.entity;

public class RequestRelationship {

    private long id;//Es el ID de la relacion
    private long idUserReceive;//Es el ID del usuario que recibe la solicitud
    private long idUserSend;//Es el ID del usuario que envia la solicitud
    private boolean state;//Es el estado de la relacion

    public RequestRelationship(long idUserReceive, long idUserSend) {
        setIdUserReceive(idUserReceive);
        setIdUserSend(idUserSend);
        setState(false);
    }

    //Este constructor solo se usa cuando se traen los datos de la base de datos
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

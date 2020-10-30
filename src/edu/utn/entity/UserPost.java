package edu.utn.entity;

import java.sql.Date;

public class UserPost {

    private long id;
    private long idUser;
    private Date datePublication;
    private String post;

    public UserPost (long idUser, String post, Date datePublication){
        setIdUser(idUser);
        setPost(post);
        setDatePublication(datePublication);
    }

    //Este constructor lo usa el get
    public UserPost(long id, long idUser, String post, Date datePublication) {
        setId(id);
        setIdUser(idUser);
        setPost(post);
        setDatePublication(datePublication);
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}

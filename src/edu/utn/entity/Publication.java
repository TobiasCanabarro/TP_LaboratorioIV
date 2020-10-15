package edu.utn.entity;

import java.sql.Date;

public class Publication {

    private long id;
    private String publication;
    private String imagePublication;
    private Date datePublication;

    public Publication(long id, String publication, String imagePublication, Date datePublication) {
        setId(id);
        setPublication(publication);
        setImagePublication(imagePublication);
        setDatePublication(datePublication);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getImagePublication() {
        return imagePublication;
    }

    public void setImagePublication(String imagePublication) {
        this.imagePublication = imagePublication;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }
}

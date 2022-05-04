/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author riadh
 */
public class Poste {
    private int id;
    private int profileId;
    private String image; // A changer dépandant comment ajouter l'image
    private String contenu;
    private int likes;
    private Date date;

    public Poste() {
    }

    public Poste(int id, int profileId, String image, String contenu, int likes, Date date) {
        this.id = id;
        this.profileId = profileId;
        this.image = image;
        this.contenu = contenu;
        this.likes = likes;
        this.date = date;
    }
    
    public Poste( int profileId, String image, String contenu, int likes, Date date) {
        
        this.profileId = profileId;
        this.image = image;
        this.contenu = contenu;
        this.likes = likes;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Poste{" + "id=" + id + ", profileId=" + profileId + ", image=" + image + ", contenu=" + contenu + ", likes=" + likes + ", date=" + date + '}';
    }
    
    
}

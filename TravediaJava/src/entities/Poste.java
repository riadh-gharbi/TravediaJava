/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

    public Poste(Object profileId, Object image, Object contenu, Object likes) {
        this.profileId = (int) profileId;
        this.image = (String) image;
        this.contenu = (String) contenu;
        this.likes = (int) likes;
    }
    
    public Poste(Object id, Object profileId, Object image, Object contenu, Object likes, Object date) {
        this.id = (int) id;
        this.profileId = (int) profileId;
        this.image = (String) image;
        this.contenu = (String) contenu;
        this.likes = (int) likes;
        this.date = (Date) date;
    }
    
    public Poste( Object profileId, Object image, Object contenu, Object likes, Object date) {
        
        this.profileId = (int) profileId;
        this.image = (String) image;
        this.contenu = (String) contenu;
        this.likes = (int) likes;
        this.date = (Date) date;
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

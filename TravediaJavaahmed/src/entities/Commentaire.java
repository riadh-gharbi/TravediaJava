/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author riadh
 */
public class Commentaire {
    private int id;
    private int posteId;
    private String contenu;
    private Date date;

    public Commentaire() {
    }

    public Commentaire(Object id, Object posteId, Object contenu, Object date) {
        this.id = (int) id;
        this.posteId = (int) posteId;
        this.contenu = (String) contenu;
        this.date = (Date) date;
    }
    
      public Commentaire( Object posteId, Object contenu, Object date) {
      
        this.posteId = (int) posteId;
        this.contenu = (String) contenu;
        this.date = (Date) date;
    }

    public Commentaire(Object id, Object contenu) {
        this.id = (int) id;
        this.contenu = (String) contenu;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosteId() {
        return posteId;
    }

    public void setPosteId(int posteId) {
        this.posteId = posteId;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", posteId=" + posteId + ", contenu=" + contenu + ", date=" + date + '}';
    }
    
    
}

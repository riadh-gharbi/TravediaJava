/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author user
 */
public class Evenement {
   private int id;
    private String nom;
    private String image;
    private String description;
    private Date datedeb;
    private Date datefin;
    private int categorie;

    public Evenement() {
    }

    public Evenement(int id, String nom, String image, String description, Date datedeb, Date datefin, int categorie) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.description = description;
        this.datedeb = datedeb;
        this.datefin = datefin;
        this.categorie = categorie;
    }

    public Evenement(String nom, String image, String description, Date datedeb, Date datefin, int categorie) {
        this.nom = nom;
        this.image = image;
        this.description = description;
        this.datedeb = datedeb;
        this.datefin = datefin;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatedeb() {
        return datedeb;
    }

    public void setDatedeb(Date datedeb) {
        this.datedeb = datedeb;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", image=" + image + ", description=" + description + ", datedeb=" + datedeb + ", datefin=" + datefin + ", categorie=" + categorie + '}';
    }
    
}

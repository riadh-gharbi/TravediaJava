/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author user
 */
public class Evenement {
   private int id;
    private String nom;
    private String image;
    private String description;
    private String datedeb;
    private String datefin;
    private String categorie;

    public Evenement() {
    }

    public Evenement(int id, String nom, String image, String description, String datedeb, String datefin, String categorie) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.description = description;
        this.datedeb = datedeb;
        this.datefin = datefin;
        this.categorie = categorie;
    }

    public Evenement(String nom, String image, String description, String datedeb, String datefin, String categorie) {
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

    public String getDatedeb() {
        return datedeb;
    }

    public void setDatedeb(String datedeb) {
        this.datedeb = datedeb;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author user
 */
public class Categorie {
     private int id;
    private String nom;
    private String image;
    private ImageView imageV;

    public Categorie(String nom, ImageView imageV) {
        this.nom = nom;
        this.imageV = imageV;
    }
    
    

    public Categorie() {
    }

    public Categorie(int id, String nom, String image) {
        this.id = id;
        this.nom = nom;
        this.image = image;
    }

    public Categorie(String nom, String image) {
        this.nom = nom;
        this.image = image;
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

    public ImageView getImageV() {
        return imageV;
    }

    public void setImageV(ImageView imageV) {
        this.imageV = imageV;
    }
    
    

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom=" + nom + ", image=" + image + '}';
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Ameni
 */
public class Region {
     int id;
     String nom;
     String image ;

    public Region(int id, String nom, String image) {
        this.id = id;
        this.nom = nom;
        this.image = image;
    }

    public Region(String nom, String image) {
        this.nom = nom;
        this.image = image;
    }

  
   

//    public Region(String toString, String toString0) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

//    public Region(String toString, String toString0) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

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

    public Region() {
    }
     
     
}

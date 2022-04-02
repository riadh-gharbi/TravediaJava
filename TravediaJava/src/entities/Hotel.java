/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author bhk
 */
public class Hotel {
    private int Id;
    private String nom;
    private String adresse;
    private String email;
    private int numTel;

    public Hotel(int Id, String nom, String adresse, String email, int numTel) {
        this.Id = Id;
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.numTel = numTel;
    }

    public Hotel(String nom, String adresse, String email, int numTel) {
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.numTel = numTel;
    }

    public Hotel() {
    }
    

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    @Override
    public String toString() {
        return "Hotel{" + "Id=" + Id + ", nom=" + nom + ", adresse=" + adresse + ", email=" + email + ", numTel=" + numTel + '}';
    }
    
    
    
}

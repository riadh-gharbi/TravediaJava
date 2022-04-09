/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Ibtihel
 */
public class Utilisateur {
    //nshoufou l entity fl symfony
    private int id,cin,numtel;
    private String email, password,nom,prenom,adresse,langue,roles;
    private int is_verified, is_blocked;
;

    public Utilisateur() {
        
    }

    
    
    
    public Utilisateur(int id, int cin, int numtel, String email, String password, String nom, String prenom, String adresse, String langue, String roles) {
        this.id = id;
        this.cin = cin;
        this.numtel = numtel;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.langue = langue;
        this.roles = roles;
    }

    public Utilisateur(int cin, int numtel, String email, String password, String nom, String prenom, String adresse, String langue, String roles) {
        this.cin = cin;
        this.numtel = numtel;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.langue = langue;
        this.roles = roles;
    }

    public Utilisateur(String email, String password, String nom, String prenom, String roles) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.roles = roles;
    }

    public Utilisateur(String email, String password, String nom, String prenom, String roles, int is_verified, int is_blocked) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.roles = roles;
        this.is_verified = is_verified;
        this.is_blocked = is_blocked;
    }

    public Utilisateur(int id, String email, String password, String nom, String prenom, String roles, int is_verified, int is_blocked) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.roles = roles;
        this.is_verified = is_verified;
        this.is_blocked = is_blocked;
    }

   
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(int is_verified) {
        this.is_verified = is_verified;
    }

    public int getIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(int is_blocked) {
        this.is_blocked = is_blocked;
    }

   
     
    
    
    
    
    
    
    
    
  
}

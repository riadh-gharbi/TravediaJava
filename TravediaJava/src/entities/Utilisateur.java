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
    private int id;
    private int cin;
    private String nom;
    private String prenom;
    private String adresse;
    private int numtel;
    private String email;
    private String langue;
    private String roles;
    private String password;
    private boolean is_verified;
    private boolean is_blocked;

    public Utilisateur() {
    }

    public Utilisateur(int cin, String nom, String prenom, String adresse, int numtel, String email, String langue, String roles, String password, boolean is_verified, boolean is_blocked) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numtel = numtel;
        this.email = email;
        this.langue = langue;
        this.roles = roles;
        this.password = password;
        this.is_verified = is_verified;
        this.is_blocked = is_blocked;
    }

    public Utilisateur(int id, int cin, String nom, String prenom, String adresse, int numtel, String email, String langue, String roles, String password, boolean is_verified, boolean is_blocked) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numtel = numtel;
        this.email = email;
        this.langue = langue;
        this.roles = roles;
        this.password = password;
        this.is_verified = is_verified;
        this.is_blocked = is_blocked;
    }

    public Utilisateur(String nom, String prenom, String email, String password, String roles) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.roles = roles;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public boolean getIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked = is_blocked;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", numtel=" + numtel + ", email=" + email + ", langue=" + langue + ", roles=" + roles + ", password=" + password + ", is_verified=" + is_verified + ", is_blocked=" + is_blocked + '}';
    }

}

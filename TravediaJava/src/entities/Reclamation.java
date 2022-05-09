/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author riadh
 */
public class Reclamation {
    private int id;
    private Integer user_id;
    private String sujet;
    private String contenu;
    private String etat_reclamation;
    private Integer reclamationResponseID;

    public Reclamation(int id, Integer user_id, String sujet, String contenu, String etat_reclamation, Integer reclamationResponseID) {
        this.id = id;
        this.user_id = user_id;
        this.sujet = sujet;
        this.contenu = contenu;
        this.etat_reclamation = etat_reclamation;
        this.reclamationResponseID = reclamationResponseID;
    }

     public Reclamation( Integer user_id, String sujet, String contenu, String etat_reclamation, Integer reclamationResponseID) {
        
        this.user_id = user_id;
        this.sujet = sujet;
        this.contenu = contenu;
        this.etat_reclamation = etat_reclamation;
        this.reclamationResponseID = reclamationResponseID;
    }
    public Reclamation() {
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Integer getReclamationResponseID() {
        return reclamationResponseID;
    }

    public void setReclamationResponseID(Integer reclamationResponseID) {
        this.reclamationResponseID = reclamationResponseID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        
        this.user_id = user_id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getEtat_reclamation() {
        return etat_reclamation;
    }

    public void setEtat_reclamation(String etat_reclamation) {
        this.etat_reclamation = etat_reclamation;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", user_id=" + user_id + ", sujet=" + sujet + ", contenu=" + contenu + ", etat_reclamation=" + etat_reclamation + ", reclamationResponseID=" + reclamationResponseID + '}';
    }

   
    
    
}

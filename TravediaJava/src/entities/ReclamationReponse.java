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
public class ReclamationReponse {
    private int id;
    private String contenu;
    private int reclamationId;

    public ReclamationReponse() {
    }

    public ReclamationReponse(int id, String contenu, int reclamationId) {
        this.id = id;
        this.contenu = contenu;
        this.reclamationId = reclamationId;
    }
 
      public ReclamationReponse(String contenu, int reclamationId) {
        
        this.contenu = contenu;
        this.reclamationId = reclamationId;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getReclamationId() {
        return reclamationId;
    }

    public void setReclamationId(int reclamationId) {
        this.reclamationId = reclamationId;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import entities.ReclamationReponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyDB;

/**
 *
 * @author riadh
 */
public class ReclamationService implements IService<Reclamation>{

    Connection cnx;
    public ReclamationService() {
       cnx = MyDB.getInstance().getConnection();
    }

    
    
    @Override
    public void ajouter(Reclamation r) {
        //put sql statement in req
        //put strings between simple quotes '' because they're VARCHAR in the database
        //Not obligatory to end the SQL message with ';'
        //Statement is requete statique
        //Should be using prepared statement like below in modify, but this works for testing sake
        
            String req = "INSERT INTO reclamation (sujet,contenu,etat_reclamation,utilisateur_id)"+
                    " values ('"+r.getSujet()+"','"+r.getContenu()+"','"+r.getEtat_reclamation()
                    +"',"+r.getUser_id()+")";
            //System.out.println(req);
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Ajout"+r.toString());
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reclamation r) {
 String req = "UPDATE Reclamation SET sujet= ?, contenu = ? , etat_reclamation = ? , utilisateur_id = ? WHERE id = ?";
        
        try {
            //Prepared statement because there are parameters
            //Requete Dynamique
            //Remplacer les ? 
            //We can use a LOOP to add many lines
            //Once we call prepareStatement, the request is pre-compiled
            PreparedStatement ps = cnx.prepareStatement(req);

              ps.setString(1,r.getSujet());
              ps.setString(2, r.getContenu());
              ps.setString(3, r.getEtat_reclamation());
              ps.setInt(4, r.getUser_id());
              ps.setInt(5, r.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
    public void ModifyReponse(int recId,int repId)
    {
        String req = "UPDATE reclamation SET reclamation_Rep_id ="+ repId+" WHERE id ="+ recId;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reponse "+ repId +" added to "+ recId);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    
    //Contrainte d'intégritée. Reclamation response
    @Override
    public void supprimer(int id) {
         String req = "DELETE from reclamation WHERE id = "+id;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> recuperer() {
 List<Reclamation> reclamations = new ArrayList<>() ;
        String req = "SELECT * from reclamation";
        try {
            Statement st = cnx.createStatement();
            ResultSet resultSet=st.executeQuery(req);
            while(resultSet.next())
            {
                Reclamation r = new Reclamation();
                //Get int or string can use the column index or column name
                 r.setSujet(resultSet.getString("sujet"));
                r.setContenu(resultSet.getString("contenu"));
                r.setEtat_reclamation(resultSet.getString("etat_reclamation"));
                r.setUser_id(resultSet.getInt("utilisateur_id"));
                r.setId(resultSet.getInt("id"));
                r.setReclamationResponseID(resultSet.getInt("reclamation_Rep_id"));
                reclamations.add(r);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return reclamations;    
    }

    @Override
    public Reclamation recuperer(int id) {
         String req = "SELECT * from reclamation where id= "+id;
         Reclamation r =new Reclamation();
          try {
            Statement st = cnx.createStatement();
            ResultSet resultSet=st.executeQuery(req);
//            if(resultSet.next())
//            {
//                System.out.println("Not a single result");
//                return null;
//            }
            resultSet.next();
            r.setSujet(resultSet.getString("sujet"));
            r.setContenu(resultSet.getString("contenu"));
            r.setEtat_reclamation(resultSet.getString("etat_reclamation"));
            r.setUser_id(resultSet.getInt("utilisateur_id"));
            r.setId(resultSet.getInt("id"));
            r.setReclamationResponseID(resultSet.getInt("reclamation_Rep_id"));
            
            System.out.println("Recupération de "+ r.toString());
            //return r;
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
          return r;
    }
    
    public ReclamationReponse recupererReponse(int recId)
    {
        String req="SELECT * from reclamation_reponse WHERE reclamation_id="+recId;
        ReclamationReponse rep = new ReclamationReponse();
        
        try {
            Statement st = cnx.createStatement();
            ResultSet resultSet = st.executeQuery(req);
            
            resultSet.next();
            rep.setContenu(resultSet.getString("contenu"));
            rep.setId(resultSet.getInt("id"));
            rep.setReclamationId(resultSet.getInt("reclamation_id"));
            System.out.println("Recuperation"+ rep.toString());
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           return rep;
    }
    
    public void ajouterReponse(ReclamationReponse rep, int recId)
    {
        //ajout de la reponse
        
            String req = "INSERT INTO reclamation_reponse (contenu,reclamation_id)"+
                    " values ('"+rep.getContenu()+"',"+recId+")";
            //System.out.println(req);
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Ajout"+rep.toString());
            ModifyReponse(recId, recupererReponse(recId).getId());
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
    }
    
    public void modifierReponse(ReclamationReponse rep)
    {
        //Modifier une reclamation
         String req = "UPDATE reclamation_reponse SET contenu ="+ rep.getContenu()+" WHERE id ="+ rep.getId();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reponse "+ rep.toString() +" modifiée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void supprimerReponse(int reponseId)
    {
       String req = "DELETE from reclamation_reponse WHERE id = "+reponseId;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

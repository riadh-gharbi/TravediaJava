/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Paiement;
import entities.Reclamation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;

/**
 *
 * @author riadh
 */
public class PaiementService implements IService<Paiement>{

    Connection cnx;
    public PaiementService() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Paiement t) {
  String req = "INSERT INTO paiement (owner_id,client_id,planning_id,prix,statut,date_creation,date_paiement,type_paiement,session_id)"+
                    " values (? , ? , ? , ? , ? , ?,?,?, ? )";
            //System.out.println(req);
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setInt(1,t.getOwnerId());
            ps.setInt(2,t.getClientId());
            ps.setInt(3,t.getPlanningId());
            ps.setDouble(4,(int) t.getPrix());
            ps.setString(5, t.getStatut());
            ps.setDate(6,new Date(t.getDate_creation().getTime()));
            
            ps.setDate(7,(t.getDate_paiement() != null)? new Date(t.getDate_paiement().getTime()): null);
            ps.setString(8, t.getType_paiement());
            ps.setString(9, t.getSessionID());
            ps.executeUpdate();
            System.out.println("Ajout"+t.toString());
        } catch (SQLException  ex) {
                System.out.println("Erreur : " + ex.getMessage());
        }    
    }

    @Override
    public void modifier(Paiement t) {
String req = "UPDATE paiement SET owner_id=?,client_id=?,planning_id=?,prix=?,statut=?,date_creation=?,date_paiement=?,type_paiement=?,session_id=? WHERE id= ?";
            //System.out.println(req);
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,t.getOwnerId());
            ps.setInt(2,t.getClientId());
            ps.setInt(3,t.getPlanningId());
            ps.setDouble(4,(int) t.getPrix());
            ps.setString(5, t.getStatut());
            ps.setDate(6,new Date(t.getDate_creation().getTime()));
            ps.setDate(7, new Date(t.getDate_paiement().getTime()));
            ps.setString(8, t.getType_paiement());
            ps.setString(9, t.getSessionID());
            ps.setInt(10, t.getId());
            ps.executeUpdate();
            System.out.println("Ajout"+t.toString());
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }        }

    @Override
    public void supprimer(int id) {
 String req = "DELETE from paiement WHERE id = "+id;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Paiement> recuperer() {
List<Paiement> paiements = new ArrayList<>() ;
        String req = "SELECT * from paiement";
        try {
            Statement st = cnx.createStatement();
            ResultSet resultSet=st.executeQuery(req);
            while(resultSet.next())
            {
                Paiement p = new Paiement();
                //Get int or string can use the column index or column name
//                 r.setSujet(resultSet.getString("sujet"));
//                r.setContenu(resultSet.getString("contenu"));
//                r.setEtat_reclamation(resultSet.getString("etat_reclamation"));
//                r.setUser_id(resultSet.getInt("utilisateur_id"));
//                r.setId(resultSet.getInt("id"));
//                r.setReclamationResponseID(resultSet.getInt("reclamation_Rep_id"));
                p.setClientId(resultSet.getInt("client_id"));
                p.setOwnerId(resultSet.getInt("owner_id"));
                p.setDate_creation(resultSet.getDate("date_creation"));
                p.setDate_paiement(resultSet.getDate("date_paiements"));
                p.setId(resultSet.getInt("id"));
                p.setPlanningId(resultSet.getInt("planning_id"));
                p.setPrix(resultSet.getFloat("prix"));
                p.setSessionID(resultSet.getString("session_id"));
                p.setStatut(resultSet.getString("statut"));
                p.setType_paiement(resultSet.getString("type_paiement"));
                
               paiements.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return paiements;       
    }

    @Override
    public Paiement recuperer(int id) {
        Paiement p = new Paiement();
        String req = "SELECT * from paiement WHERE id =" + id;
        try {
            Statement st = cnx.createStatement();
            ResultSet resultSet=st.executeQuery(req);
            resultSet.next();
                //Get int or string can use the column index or column name
//                 r.setSujet(resultSet.getString("sujet"));
//                r.setContenu(resultSet.getString("contenu"));
//                r.setEtat_reclamation(resultSet.getString("etat_reclamation"));
//                r.setUser_id(resultSet.getInt("utilisateur_id"));
//                r.setId(resultSet.getInt("id"));
//                r.setReclamationResponseID(resultSet.getInt("reclamation_Rep_id"));
                p.setClientId(resultSet.getInt("client_id"));
                p.setOwnerId(resultSet.getInt("owner_id"));
                p.setDate_creation(resultSet.getDate("date_creation"));
                p.setDate_paiement(resultSet.getDate("date_paiements"));
                p.setId(resultSet.getInt("id"));
                p.setPlanningId(resultSet.getInt("planning_id"));
                p.setPrix(resultSet.getFloat("prix"));
                p.setSessionID(resultSet.getString("session_id"));
                p.setStatut(resultSet.getString("statut"));
                p.setType_paiement(resultSet.getString("type_paiement"));
                
               
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return p;     }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class EvenementService {
     Connection cnx;

    public EvenementService() {
        cnx =MyDB.getInstance().getConnection();
    }

    public void ajouter(Evenement evt) {
        String req ="insert into evenement(nom,image,description,datedeb,datefin,categorie_id)"+ "values ('"+evt.getNom() +"', '"+evt.getImage()+"', '"+evt.getDescription()+"', DATE '"+evt.getDatedeb()+"', DATE '"+evt.getDatefin()+"','"+evt.getCategorie()+"')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement ajoute !!");
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
           // Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void modifier(Evenement evt) {
        String req ="update evenement set nom = ?, image = ?, description=?, datedeb=?, datefin=?, categorie_id=? where id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, evt.getNom());
            ps.setString(2, evt.getImage());
            ps.setString(3, evt.getDescription());
            ps.setDate(4,evt.getDatedeb());
            ps.setDate(5,evt.getDatefin());
            ps.setInt(6, evt.getCategorie());
            ps.setInt(7, evt.getId());
            ps.executeUpdate();
            System.out.println("Evenement modifie !!");
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     public List<Evenement> recuperer() {
        List<Evenement> evenements=new ArrayList<>();
        String req ="select * from evenement";
         try {
            Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while(rs.next()){
                 Evenement evt = new Evenement();
                 evt.setId(rs.getInt(1));
                 evt.setNom(rs.getString("nom"));
                 evt.setImage(rs.getString("image"));
                 evt.setDescription(rs.getString("description"));
                 evt.setDatedeb(rs.getDate("datedeb"));
                 evt.setDatefin(rs.getDate("datefin"));
                 evt.setCategorie(rs.getInt("categorie_id"));
                 evenements.add(evt);  
             }
             
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
           // Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
         return evenements;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
      public void supprimer(Evenement evt) {
        try {
            String req = "delete from evenement where id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, evt.getId());
            pst.executeUpdate();
            System.out.println("Evenement supprime !!");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
     
    
    
}

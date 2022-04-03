/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;

/**
 *
 * @author user
 */
public class CategorieService {
    Connection cnx;

    public CategorieService() {
         cnx =MyDB.getInstance().getConnection();
    }
    
    
    
    public void ajouter(Categorie cat) {
        String req ="insert into categorie(nom,image)"+ "values ('"+cat.getNom() +"', '"+cat.getImage()+"')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
           // Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void modifier(Categorie cat) {
        String req ="update categorie set nom = ?, image = ? where id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, cat.getNom());
            ps.setString(2, cat.getImage());
            ps.setInt(3, cat.getId());
            ps.executeUpdate();
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     public List<Categorie> recuperer() {
        List<Categorie> categories=new ArrayList<>();
        String req ="select * from categorie";
         try {
            Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while(rs.next()){
                 Categorie cat = new Categorie();
                 cat.setId(rs.getInt(1));
                 cat.setNom(rs.getString("nom"));
                 cat.setImage(rs.getString("image"));
                 categories.add(cat);  
             }
             
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
           // Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
         return categories;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    
}

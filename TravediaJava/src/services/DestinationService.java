/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Destination;
import entities.Region;
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
 * @author Ameni
 */
public class DestinationService implements IService<Destination>{
 Connection cnx;
public DestinationService() {
    cnx=MyDB.getInstance().getConnection();
}

    @Override
    public void ajouter(Destination t) {
        
  String req = "insert into Destination (nom,Description,image)"+"values('"+t.getNom()+"','"+t.getDescription()+"','"+t.getImage()+"')";
        try{
        Statement st= cnx.createStatement();
        st.executeUpdate(req);
            System.out.println("add success");
        }catch (SQLException ex ) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Destination t) {
  String req = "update Destination set nom=? , description=?  where id=?";
      
        try{
    PreparedStatement pst= cnx.prepareStatement(req);
            pst.setString(1,t.getNom());
            pst.setString(2,t.getDescription());
           pst.setInt(4,t.getId());

            pst.executeUpdate();
            System.out.println(" update success");
        }catch (SQLException ex ) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(int id) {
      String req = "DELETE from Destination WHERE id = "+id;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Destination> recuperer() {
String req = "select * from Destination ";
      List<Destination>destinations=new ArrayList<>();
        try{
    Statement pst= cnx.createStatement();
        
            pst.executeQuery(req);
            ResultSet rs =pst.executeQuery(req);
            while(rs.next()){
                Destination d=new Destination();
                d.setId(rs.getInt(1));
                d.setNom(rs.getString("nom"));
                d.setDescription(rs.getString("description"));
                destinations.add(d);
            }
            System.out.println(" recuperation success");
        }catch (SQLException ex ) {
            System.out.println(ex.getMessage());
        } 
        return destinations;    }

    @Override
    public Destination recuperer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

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
public class RegionService implements IService<Region>{
Connection cnx;
public RegionService() {
    cnx=MyDB.getInstance().getConnection();
}
    @Override
    public void ajouter(Region t) {
                    System.out.println(" 111");

 String req = "insert into Region (nom,image)"+"values('"+t.getNom()+"','"+t.getImage()+"')";
                     System.out.println(" 22");

        try{
        Statement st= cnx.createStatement();
        st.executeUpdate(req);
            System.out.println("add success");
        }catch (SQLException ex ) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Region t) {
  String req = "update Region set nom=?  where id=?";
      
        try{
    PreparedStatement ps= cnx.prepareStatement(req);
            ps.setString(1,t.getNom());
         
           ps.setInt(4,t.getId());

            ps.executeUpdate();
            System.out.println(" update success");
        }catch (SQLException ex ) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(int id) {
      String req = "DELETE from Region WHERE id = "+id;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Region> recuperer() {
String req = "select * from Region ";
      List<Region>regions=new ArrayList<>();
        try{
    Statement ps= cnx.createStatement();
        
            ps.executeQuery(req);
            ResultSet rs =ps.executeQuery(req);
            while(rs.next()){
                Region r=new Region();
                r.setId(rs.getInt(1));
                r.setNom(rs.getString("nom"));
               
                regions.add(r);
            }
            System.out.println(" recuperation success");
        }catch (SQLException ex ) {
            System.out.println(ex.getMessage());
        } 
        return regions;    }

    @Override
    public Region recuperer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

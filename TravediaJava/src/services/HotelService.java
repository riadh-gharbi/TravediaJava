/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Hotel;
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
 * @author Razer
 */
public class HotelService implements IService<Hotel>{
    
     Connection cnx;

    public HotelService() {
    cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Hotel t) {
        try {
            String req = "insert into Hotel (nom,adresse,email,num_tel)"+"VALUES('"+t.getNom() +"','"+t.getAdresse()+"','"+t.getEmail()+"',"+t.getNumTel()+")";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Hotel Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void modifier(Hotel t) {
        try {
            String req = "update Hotel set nom =?,adresse=?,email=?,num_tel=? where Id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,t.getNom());
            ps.setString(2,t.getAdresse());
            ps.setString(3,t.getEmail());
            ps.setInt(4,t.getNumTel());
            ps.setInt(5,t.getId());
            ps.executeUpdate();
            System.out.println("Hotel Updated nice");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        PreparedStatement ps =null;
	try {
	String sql ="delete from Hotel where id=?";
	ps=cnx.prepareStatement(sql);
	ps.setInt(1,id);
	ps.executeUpdate();
        
            System.out.println("Hotel Poof");
	
	}catch(SQLException ex) {
		System.out.println(ex.getMessage());
	}
    }

    @Override
    public List<Hotel> recuperer() {
       List<Hotel> Hotels = new ArrayList<>();
        try {
            String req = "select * from Hotel";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                 Hotel h = new Hotel();
                 h.setId(rs.getInt(1));
                 h.setNom(rs.getString(2));
                 h.setAdresse(rs.getString(3));
                 h.setEmail(rs.getString(4));
                 h.setNumTel(rs.getInt(5));
                 Hotels.add(h);
            }
                    } catch (SQLException ex) {
            System.out.println("PogU");
        }
        return Hotels;
    }

    @Override
    public Hotel recuperer(int id) {
         String req = "select * from hotel where id = "+id;
             Hotel hs = new Hotel();
         try {
                         Statement st = cnx.createStatement();
             ResultSet result = st.executeQuery(req);
             result.next();
             
             hs.setId(result.getInt("id"));
             hs.setNom(result.getString("nom"));
             hs.setAdresse(result.getString("adresse"));
             hs.setEmail(result.getString("email"));
             hs.setNumTel(result.getInt("num_tel"));
         } catch (SQLException ex) {
             System.out.println(ex);
         }
         return hs  ;
                
               
                }
                    
}



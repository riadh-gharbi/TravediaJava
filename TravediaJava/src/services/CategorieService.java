/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Categorie;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
            System.out.println("Categorie ajoutee !!");
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
            System.out.println("Categorie modifiee !!");
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
                // cat.setImage("file:C:\\Users\\user\\OneDrive\\Desktop\\images"+ rs.getString("image"));
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
     
      public void supprimer(Categorie cat) {
        try {
            String req = "delete from categorie where id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, cat.getId());
            pst.executeUpdate();
            System.out.println("Categorie supprimee !!");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
     
     public List<Categorie> filtrerCategorieParNom(String cat) {
    	
    	List<Categorie> listT = new ArrayList<>();
    	
    	String req = "Select * from categorie where nom LIKE %?%";
    	
        try {
			PreparedStatement pstt = cnx.prepareStatement(req);
			pstt.setString(1, cat);
			ResultSet rs = pstt.executeQuery();
			
            while (rs.next()) {
            	
                listT.add(new Categorie(rs.getInt(1),rs.getString(2),rs.getString(3)));
           
            }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    return listT;
    }
     
     public List<Categorie> recupererID() {
        List<Categorie> categories=new ArrayList<>();
        String req ="select id from categorie";
         try {
            Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while(rs.next()){
                 Categorie cat = new Categorie();
                 cat.setId(rs.getInt(1));
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
     
     public String[] getData() throws MalformedURLException, ProtocolException, IOException {
 
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Tunis&appid=1aea29dd7370d3f03d213e6ddea2d439&units=metric");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            ObjectMapper mapper = new ObjectMapper();
            String[] sa = new String[7];
            JsonNode jsonNode = mapper.readTree(connection.getInputStream());
            String temperature = jsonNode.get("main").get("temp").toString();
            sa[0] = temperature;
            String humidity = jsonNode.get("main").get("humidity").toString();
            sa[1] = humidity;
            String description = jsonNode.get("weather").get(0).get("description").toString();
            sa[2] = description;
            String pressure = jsonNode.get("main").get("pressure").toString();
            sa[3] = pressure;
              String main = jsonNode.get("weather").get(0).get("main").toString();
            sa[4] = main;
 
            return sa;
        } catch (Exception ex) {
             System.out.println(ex.getMessage());
        }
        return null;
    }
     
}

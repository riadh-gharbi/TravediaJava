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
import java.util.stream.Collectors;
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
        
  String req = "insert into `destination` (nom,description,image,region_id,latitude,longitude) "+"values(?,?,?,?,?,?)";
  try{
        PreparedStatement st= cnx.prepareStatement(req);
        st.setString(1, t.getNom());
        st.setString(2, t.getDescription());
        st.setString(3, t.getImage());
        st.setInt(4, t.getId_region());
        st.setString(5,t.getLatitude().isEmpty()?null:t.getLatitude());
        st.setString(6, t.getLongitude().isEmpty()?null:t.getLongitude());
        System.out.println("st :"+ st);
        st.executeUpdate();
            System.out.println("add success");
        }catch (SQLException ex ) {
            System.err.println("Ajouter dest Error"+ex.getMessage()+" "+ ex.getSQLState()+" "+ ex.getCause());
        }    }

    @Override
    public void modifier(Destination t) {
  String req = "update Destination set nom=? , description=?  where id=?";
      
        try{
    PreparedStatement pst= cnx.prepareStatement(req);
            pst.setString(1,t.getNom());
            pst.setString(2,t.getDescription());
           pst.setInt(3,t.getId());

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
    
   

   
//    public List<Destination> getDestinationParRegion(int id) {
//  String req = "select * from  Destination INNER JOIN Region ON destination.id_region = region.id ";
//              //  $sql = 'SELECT * FROM region INNER JOIN destination ON destination.region_id = '.$id.' AND region.id = '.$id.'';
//                List<Destination>destinations=new ArrayList<>();
//        try{
//    Statement pst= cnx.createStatement();
//        
//            pst.executeQuery(req);
//            ResultSet rs =pst.executeQuery(req);
//            while(rs.next()){
//                Destination d=new Destination();
//                d.setId(rs.getInt(1));
//                d.setNom(rs.getString("nom"));
//                d.setDescription(rs.getString("description"));
//                destinations.add(d);
//            }
//            System.out.println(" recuperation success");
//        }catch (SQLException ex ) {
//            System.out.println(ex.getMessage());
//        } 
//        return destinations;    }
public List<Destination> getDestinationParRegion(int id) {
  String req = "select * from  destination WHERE region_id = "+id;
              //  $sql = 'SELECT * FROM region INNER JOIN destination ON destination.region_id = '.$id.' AND region.id = '.$id.'';
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
//    public List<Destination> RecherchedestinationbyNom(String nomCentre) {
//        List<Destination> centredecampings = new ArrayList<>();
//        String query = "Select `nomCentre`, `adresseCentre`, `prixCentre`, `typeCentre`, `VilleCentre`, `gouvernorat` from `centre de camping` WHERE `nomCentre`=? ";
//        try {
//            PreparedStatement ste = cnx.prepareStatement(query);
//            ste.setString(1, nomCentre);
//            ResultSet rs = ste.executeQuery();
//            while (rs.next()) {
//                centredecamping centre = new centredecamping();
//                centre.setNomCentre(rs.getString("NomCentre"));
//                centre.setAdresseCentre(rs.getString("AdresseCentre"));
//                centre.setPrixCentre(rs.getFloat("prixCentre"));
//                centre.setTypeCentre(rs.getString("TypeCentre"));
//                centre.setVilleCentre(rs.getString("VilleCentre"));
//                centre.setGouvernorat(rs.getString("Gouvernorat"));
//                centre.setNomCentre(rs.getString("NomCentre"));
//
//                centredecampings.add(centre);
//
//            }
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return centredecampings;
//
//    }
//     public List<Destination> trier(String Nom){
//        List<Destination> dest=new ArrayList<>();
//        Destination destination=new Destination();
//        List<Destination> resultat = dest.stream().filter(user -> Nom.equals(destination.getNom())).collect(Collectors.toList());
//        return resultat;
//    }
    public Destination recupererL() {
        String req = "select * from destination where id =(select MAX(id) from destination) ";
             Destination hs = new Destination();
         try {
                         Statement st = cnx.createStatement();
             ResultSet result = st.executeQuery(req);
             result.next();
             
             hs.setId(result.getInt("id"));
             hs.setNom(result.getString("nom"));
             hs.setDescription(result.getString("description"));
             //hs.setEmail(result.getString("email"));
             //hs.setNumTel(result.getInt("num_tel"));
         } catch (SQLException ex) {
             System.out.println(ex);
         }
         return hs  ;
                
               
                }
    
    
}

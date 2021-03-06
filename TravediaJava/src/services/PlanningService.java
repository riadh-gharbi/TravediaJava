/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Hotel;
import entities.Planning;
import entities.Destination;
import entities.Evenement;
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
 * @author Razer
 */
public class PlanningService implements IService<Planning> {
    Connection cnx;

    public PlanningService() {
    cnx = MyDB.getInstance().getConnection();
    }
    public void ajouter_planning_hotel(Hotel t , Planning p){
        try{
     String req = "insert into planning_hotel(planning_id,hotel_id)"+"VALUES("+p.getId()+","+t.getId()+")";
     Statement st = cnx.createStatement();
     st.executeUpdate(req);
           // System.out.println("Done");
        } catch (SQLException ex){
            System.out.println(ex.getMessage());        
       }
          
    
    }
    public void ajouter_planning_destination(Destination d , Planning p){
        try{
     String req = "insert into planning_destination(planning_id,destination_id)"+"VALUES("+p.getId()+","+d.getId()+")";
     Statement st = cnx.createStatement();
     st.executeUpdate(req);
            System.out.println("Done");
        } catch (SQLException ex){
            System.out.println(ex.getMessage());        
       }
          
    
    }
    public void ajouter_planning_evenement(Evenement e , Planning p){
        try{
     String req = "insert into planning_evenement(planning_id,evenement_id)"+"VALUES("+p.getId()+","+e.getId()+")";
     Statement st = cnx.createStatement();
     st.executeUpdate(req);
            System.out.println("Done");
        } catch (SQLException ex){
            System.out.println(ex.getMessage());        
       }
          
    
    }

    @Override
    public void ajouter(Planning t) {
        try {
            
            String req = "insert into planning (utilisateur_id,date_depart,date_fin,prix,type_plan,description)"+"VALUES("+t.getVoyageurId()+",DATE"+t.getDateDepart()+",DATE"+t.getDateFin()+","+t.getPrix()+",'"+t.getTypePlan()+"','"+t.getDescription()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Planning Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
//    public void ajouter(Planning t,Destination d , Hotel h,Evenement e) {
//        try {
//            
//            String req = "insert into planning (utilisateur_id,date_depart,date_fin,prix,type_plan,description)"+"VALUES("+t.getVoyageurId()+",DATE"+t.getDateDepart()+",DATE"+t.getDateFin()+","+t.getPrix()+",'"+t.getTypePlan()+"','"+t.getDescription()+"')";
//            Statement st = cnx.createStatement();
//            st.executeUpdate(req);
//            ajouter_planning_destination(d, t);
//            ajouter_planning_evenement(e, t);
//            ajouter_planning_hotel(h, t);
//            System.out.println("Planning Added");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    public void ajouter(Planning t,Destination d , Hotel h,Evenement e) {
        
            
            String req = "insert into planning (utilisateur_id,date_depart,date_fin,prix,type_plan,description)"+"VALUES(?,?,?,?,?,?)";
           try {
            PreparedStatement ps = cnx.prepareStatement(req);
            
            
            ps.setInt(1,t.getVoyageurId());
            ps.setDate(2, new Date(t.getDateDepart().getTime()));
            ps.setDate(3, new Date(t.getDateFin().getTime()));
            ps.setInt(4, t.getPrix());
            ps.setString(5,t.getTypePlan());
            ps.setString(6,t.getDescription());
            
            
            ps.executeUpdate();
            ajouter_planning_destination(d, t);
            ajouter_planning_evenement(e, t);
            ajouter_planning_hotel(h, t);
            System.out.println("Ajout"+t.toString());
        } catch (SQLException  ex) {
                System.out.println("Erreur : " + ex.getMessage());
        }    
    }

    @Override
    public void modifier(Planning t) {
       try {
            String req = "update planning set utilisateur_id =?,date_depart=?,date_fin=?,prix=?,type_plan=?,description=? where Id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,t.getVoyageurId());
            //ps.setInt(2,t.getActualiteId());
            ps.setDate(2, (Date) t.getDateDepart());
            ps.setDate(3,(Date) t.getDateFin());
            ps.setInt(4,t.getPrix());
            ps.setString(5,t.getTypePlan());
            ps.setString(6,t.getDescription());
            ps.executeUpdate();
            System.out.println("Planning Updated nice");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
       PreparedStatement ps =null;
	try {
	String sql ="delete from planning where id=?";
	ps=cnx.prepareStatement(sql);
	ps.setInt(1,id);
	ps.executeUpdate();
        
            System.out.println("planning Poof");
	
	}catch(SQLException ex) {
		System.out.println(ex.getMessage());
	}
    }

    @Override
    public List<Planning> recuperer() {
            List<Planning> Plannigs = new ArrayList<>();
            String req = "select * from planning";
       try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                 Planning h = new Planning();
                 h.setId(rs.getInt(1));
                 h.setVoyageurId(rs.getInt(2));
                 //h.setActualiteId(rs.getInt(2));
                 h.setDateDepart(rs.getDate(3));
                 h.setDateFin(rs.getDate(4));
                 h.setPrix(rs.getInt(5));
                 h.setTypePlan(rs.getString(6));
                 h.setDescription(rs.getString(7));
                // h.setHotelId(rs.getInt(8));
                 Plannigs.add(h);
            }
                    } catch (SQLException ex) {
            System.out.println("PogU");
        }
        return Plannigs;
    }

    @Override
    public Planning recuperer(int id) {
        String req = "select * from planning where id = "+id;
             Planning hs = new Planning();
             
         try {
                         Statement st = cnx.createStatement();
             ResultSet result = st.executeQuery(req);
             result.next();
             hs.setId(result.getInt("id"));
             hs.setVoyageurId(result.getInt("utilisateur_id"));
             //hs.setActualiteId(result.getInt("actualite_id"));
             hs.setDateDepart(result.getDate("date_depart"));
             hs.setDateFin(result.getDate("date_fin"));
             hs.setPrix(result.getInt("prix"));
             hs.setTypePlan(result.getString("type_plan"));
             hs.setDescription(result.getString("description"));
             // hs.setHotelId(result.getInt("id"));
             } catch (SQLException ex) {
             System.out.println(ex);
         }
         return hs  ;
                
               
                }
    
    public Planning recupererL() {
        String req = "select * from planning where id =(select MAX(id) from planning) ";
             Planning hs = new Planning();
         try {
                         Statement st = cnx.createStatement();
             ResultSet result = st.executeQuery(req);
             result.next();
             
             hs.setId(result.getInt("id"));
             hs.setVoyageurId(result.getInt("utilisateur_id"));
             //hs.setActualiteId(result.getInt("actualite_id"));
             hs.setDateDepart(result.getDate("date_depart"));
             hs.setDateFin(result.getDate("date_fin"));
             hs.setPrix(result.getInt("prix"));
             hs.setTypePlan(result.getString("type_plan"));
             hs.setDescription(result.getString("description"));
         } catch (SQLException ex) {
             System.out.println(ex);
         }
         return hs  ;
                
               
                }
    
//    public List<Hotel> recupererHOTEL() {
//            List<Hotel> hotelss = new ArrayList<>();
//            String req = "select hotel_id from planning_hotel INNER JOIN planning on planning_hotel.planning_id=planning.id";
//       try {
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(req);
//            
//            while(rs.next()){
//                 Hotel h = new Hotel();
//                 h.setId(rs.getInt(1));
//                 hotelss.add(h);
//            }
//                    } catch (SQLException ex) {
//            System.out.println("PogU");
//        }
//        return hotelss;
//    }
   
    
    }
    


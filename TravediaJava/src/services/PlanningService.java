/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Planning;
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

    @Override
    public void ajouter(Planning t) {
        try {
            String req = "insert into planning (utilisateur_id,date_depart,date_fin,prix,type_plan,description)"+"VALUES("+t.getVoyageurId()+","+t.getDateDepart()+","+t.getDateFin()+","+t.getPrix()+",'"+t.getTypePlan()+"','"+t.getDescription()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Planning Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Planning t) {
       try {
            String req = "update planning set utilisateur_id =?,date_depart=?,date_fin=?,prix=?,type_plan=?,description=? where Id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,t.getVoyageurId());
            //ps.setInt(2,t.getActualiteId());
            ps.setDate(3, (Date) t.getDateDepart());
            ps.setDate(4,(Date) t.getDateFin());
            ps.setInt(5,t.getPrix());
            ps.setString(6,t.getTypePlan());
            ps.setString(6,t.getDescription());
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
            String req = "select * from Planning";
       try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                 Planning h = new Planning();
                 h.setId(rs.getInt(1));
                 h.setVoyageurId(rs.getInt(2));
                 //h.setActualiteId(rs.getInt(3));
                 h.setDateDepart(rs.getDate(4));
                 h.setDateFin(rs.getDate(5));
                 h.setPrix(rs.getInt(6));
                 h.setTypePlan(rs.getString(7));
                 h.setDescription(rs.getString(8));
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
    }
    


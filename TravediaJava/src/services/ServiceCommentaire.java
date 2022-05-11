/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;

/**
 *
 * @author Cooler Master
 */
public class ServiceCommentaire implements IService<Commentaire>{
    Connection con = MyDB.getInstance().getConnection();
    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
    @Override
    public void ajouter(Commentaire t) {
       String query = "INSERT INTO `commentaire`(`id`,`poste_id`, `contenu`,`date`) VALUES ('" + t.getId()+ "','"  + t.getPosteId()+ "','" + t.getContenu()+ "','" + t.getDate()+ "')";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Commentaire> getById(int id) {
        List<Commentaire> com = new ArrayList<>();
       String query = "select * from commentaire where poste_id=" + id;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                Commentaire e = new Commentaire(rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4));
                com.add(e);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return  com;
    }

    
    public List<Commentaire> getAll() {
         List<Commentaire> list = new ArrayList<>();
        String query = "Select * from `commentaire`";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Commentaire e = new Commentaire(rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4));
                list.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    //@Override
    //public void modifier(Commentaire t){}
    
    public boolean modif(Commentaire t) {
         String query = "UPDATE `commentaire` SET `id`=?,`poste_id`=?,`contenu`=?,`date`=? WHERE `id`=" + t.getId();
        boolean rowUpdated = false;
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setObject(1, t.getId());
            statement.setObject(2, t.getPosteId());
            statement.setObject(3, t.getContenu());
            statement.setObject(4, t.getDate());

            rowUpdated = statement.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rowUpdated;
    }

    
    public boolean supprimer(Commentaire t) {
        String query = "DELETE FROM commentaire where id=? ";

        boolean rowDeleted = false;
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, t.getId());
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rowDeleted;
    }

    
    public Commentaire getOne(Commentaire t) {
         System.out.println(t.getId() + "---80");
        String query = "select * from `commentaire` where id=" + t.getId();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                 Commentaire e = new Commentaire(rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4));
                return e;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return new Commentaire();
    }
    public void supprimerbyId(int id) {
        String query = "DELETE FROM commentaire where id=?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void modifier(Commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commentaire> recuperer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commentaire recuperer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

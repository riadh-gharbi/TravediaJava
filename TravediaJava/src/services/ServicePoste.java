/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Poste;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;

/**
 *
 * @author Cooler Master
 */
public class ServicePoste implements IService<Poste> {

    Connection con = MyDB.getInstance().getConnection();
    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");

    @Override
    public void ajouter(Poste t) {
        String query = "INSERT INTO `poste`(`id`,`profile_id`, `image`, `contenu`, `likes`, `date`) VALUES ('" + t.getId() + "','" + t.getProfileId() + "','" + t.getImage() + "','" + t.getContenu() + "','" + t.getLikes() + "','" + t.getDate() + "')";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*@Override
    public Poste getById(int id) {
        String query = "select * from poste where id=" + id;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Poste e = new Poste(rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4), rs.getObject(5), rs.getObject(6));

                return e;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return new Poste();
    }*/
    
    public Poste getById(int id) {
        String query = "select * from poste where id=" + id;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Poste e = new Poste(rs.getObject(1), rs.getObject(2));

                return e;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return new Poste();
    }

    
    public List<Poste> getAll() {
        List<Poste> list = new ArrayList<>();
        String query = "Select * from `poste`";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Poste e = new Poste(rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4), rs.getObject(5), rs.getObject(6));
                list.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    
    public boolean modif(Poste t) {
        String query = "UPDATE `poste` SET `id`=?,`profile_id`=?,`image`=?,`contenu`=?,`likes`=?,`date`=? WHERE `id`=" + t.getId();
        boolean rowUpdated = false;
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setObject(1, t.getId());
            statement.setObject(2, t.getProfileId());
            statement.setObject(3, t.getImage());
            statement.setObject(4, t.getContenu());
            statement.setObject(5, t.getLikes());
            statement.setObject(6, t.getDate());

            rowUpdated = statement.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rowUpdated;
    }

    
    public boolean supprimer(Poste t) {
        String query = "DELETE FROM poste where id=? ";

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

    
    public Poste getOne(Poste t) {
        System.out.println(t.getId() + "---80");
        String query = "select * from `poste` where id=" + t.getId();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Poste e = new Poste(rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4), rs.getObject(5), rs.getObject(6));
                return e;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return new Poste();
    }

    public void supprimerbyId(int id) {
        String query = "DELETE FROM poste where id=?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Poste> recuperer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poste recuperer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Poste t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

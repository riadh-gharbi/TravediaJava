/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Post;
import java.sql.Connection;
import java.sql.Date;
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
public class ServicePost implements IService<Post> {

    Connection con = MyDB.getInstance().getConnection();
    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");

   
    
    
    
    
    
    
    
    
    @Override
    public void ajouter(Post t) {
       String query = "INSERT INTO `post`(`date`, `caption`, `image`) VALUES ('" + new Date(t.getDate().getTime()) + "','" + t.getCaption() + "','" + t.getImage() + "')";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public Post getById(int id) {
        String query = "select * from post where id=" + id;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Post e = new Post(rs.getObject(4), rs.getObject(5), rs.getObject(6), rs.getObject(7), rs.getObject(8), rs.getObject(9));
                return e;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return new Post();
    }

    @Override
    public List<Post> getAll() {
        String sql = "select * from post";
        List<Post> posts= new ArrayList<>();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                            Post e = new Post(rs.getObject(4), rs.getObject(5), rs.getString(6), rs.getObject(7), rs.getObject(8), rs.getObject(9));
                             posts.add(e);
            }
        
        }catch (SQLException ex){ System.out.println(ex.getMessage());}
        return posts;
    }

    @Override
    public boolean modifier(Post t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimer(Post t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Post getOne(Post t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

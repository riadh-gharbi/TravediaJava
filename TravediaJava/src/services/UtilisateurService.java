/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Utilisateur;
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
 * @author Ibtihel
 */
public class UtilisateurService implements IService<Utilisateur>{


    Connection cnx;
    public UtilisateurService() {
       cnx = MyDB.getInstance().getConnection();
    }

    
    
    @Override
    public void ajouter(Utilisateur user) {
        //put sql statement in req
        //put strings between simple quotes '' because they're VARCHAR in the database
        //Not obligatory to end the SQL message with ';'
        //Statement is requete statique
        //Should be using prepared statement like below in modify, but this works for testing sake
        
            String req = "INSERT INTO Utilisateur (nom,prenom,email,password,roles,is_verified,is_blocked)"+
                    " values  ('"+user.getNom()+"','"+user.getPrenom()+"','"+user.getEmail() +"','"+user.getPassword()+"','"+user.getRoles() +"','"+user.getIs_verified()+"','"+user.getIs_blocked()+"' )";
                  
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("utilisateur ajouté");
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Utilisateur user) {
      String req = "UPDATE Utilisateur SET nom= ?, prenom = ? , email = ? , password = ?, roles = ? WHERE id = ?";
        
        try {
            //Prepared statement because there are parameters
            //Requete Dynamique
            //Remplacer les ? 
            //We can use a LOOP to add many lines
            //Once we call prepareStatement, the request is pre-compiled
            PreparedStatement us = cnx.prepareStatement(req);

              us.setString(1,user.getNom());
              us.setString(2, user.getPrenom());
              us.setString(3, user.getEmail());
              us.setString(4, user.getPassword());
              us.setString(5, user.getRoles());
              us.setInt(6, user.getId());
            us.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    
    }

    @Override
    public void supprimer(int id) {
       String req = "DELETE from Utilisateur WHERE id = "+id;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public List<Utilisateur> recuperer() {
     
        String req = "select * from Utilisateur ";
      List<Utilisateur>utilisateurs=new ArrayList<>();
        try{
    Statement us= cnx.createStatement();
        
            us.executeQuery(req);
            ResultSet rs =us.executeQuery(req);
            while(rs.next()){
                Utilisateur u =new Utilisateur();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setRoles(rs.getString("roles"));
                
                utilisateurs.add(u);
            }
            System.out.println(" recuperation success");
        }catch (SQLException ex ) {
            System.out.println(ex.getMessage());
        } 
        return utilisateurs;
    }

    
    @Override
    public Utilisateur recuperer(int id) {
         String req = "SELECT * from Utilisateur where id= "+id;
         Utilisateur u =new Utilisateur();
          try {
            Statement us = cnx.createStatement();
            ResultSet resultSet=us.executeQuery(req);
//            if(resultSet.next())
//            {
//                System.out.println("Not a single result");
//                return null;
//            }
            resultSet.next();
            u.setNom(resultSet.getString("nom"));
            u.setPrenom(resultSet.getString("prenom"));
            u.setEmail(resultSet.getString("email"));
            u.setRoles(resultSet.getString("roles"));
            u.setId(resultSet.getInt("id"));
            
            System.out.println("Recupération de "+ u.toString());
            //return r;
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
          return u;
    }



    
}

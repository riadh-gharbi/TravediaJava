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
import java.util.Random;
import util.BCrypt;
import util.MyDB;
import util.Session;

/**
 *
 * @author Ibtihel
 */
public class UtilisateurService implements IService<Utilisateur> {

    Connection cnx;
    private static int code;

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

        String req = "INSERT INTO utilisateur (nom,prenom,email,password,roles,is_verified,is_blocked)"
                + " values  ('" + user.getNom() + "','" + user.getPrenom() + "','" + user.getEmail() + "','" + user.getPassword() + "','" + user.getRoles() + "'," + false + "," + false + " )";

        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Utilisateur user) {
        String req = "UPDATE utilisateur SET nom= ?, prenom = ? , email = ? , password = ?, roles = ? WHERE id = ?";

        try {
            //Prepared statement because there are parameters
            //Requete Dynamique
            //Remplacer les ?
            //We can use a LOOP to add many lines
            //Once we call prepareStatement, the request is pre-compiled
            PreparedStatement us = cnx.prepareStatement(req);

            us.setString(1, user.getNom());
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
        String req = "DELETE from utilisateur WHERE id = " + id;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Utilisateur> recuperer() {

        String req = "select * from utilisateur ";
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            Statement us = cnx.createStatement();

            us.executeQuery(req);
            ResultSet rs = us.executeQuery(req);
            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setRoles(rs.getString("roles"));

                utilisateurs.add(u);
            }
            System.out.println(" Recuperation Avec Success");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return utilisateurs;
    }

    @Override
    public Utilisateur recuperer(int id) {
        String req = "SELECT * from utilisateur where id= " + id;
        Utilisateur u = new Utilisateur();
        try {
            Statement us = cnx.createStatement();
            ResultSet resultSet = us.executeQuery(req);
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

            System.out.println("Recupération de " + u.toString());
            //return r;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }

    @Override
    public void blockUser(int id) {
        String req = "UPDATE utilisateur SET is_blocked=1 where id='" + id + "'";

        try {

            PreparedStatement us = cnx.prepareStatement(req);
            us.executeUpdate(req);
            System.out.println("utilisateur bloqué");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void unblockUser(int id) {
        String req = "UPDATE utilisateur SET is_blocked=0 where id='" + id + "'";
        try {
            PreparedStatement us = cnx.prepareStatement(req);

            us.executeUpdate(req);
            System.out.println("Utilisateur unbloqué");

        } catch (SQLException ex) {
        }
    }

    @Override
    public boolean isEmailValid(String email) {
        boolean result = false;
        /*try {
            InternetAddress AdrEmail = new InternetAddress(email);
            AdrEmail.validate();
            result = true;
        } catch (AddressException ex) {
        }*/
        return result;
    }

    @Override
    public int generateCode() {
        Random numb = new Random();
        UtilisateurService.code = numb.nextInt(999999);
        System.out.println(code);

        return code;
    }

    @Override
    public boolean isCodeValid(int userCode) {
        boolean isValid = false;
        if (UtilisateurService.code == userCode) {
            isValid = true;
            System.out.println("Code Correct");
            return isValid;
        } else {
            System.out.println("Code Incorrect");
        }
        return isValid;
    }

    @Override
    public Utilisateur findByEmail(String email) {
        Utilisateur u = null;
        try {
            String req = "select * from utilisateur where email=? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new Utilisateur(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getBoolean(11),
                        rs.getBoolean(12));
                System.out.println("last");

            }
            System.out.print("correct");
            System.out.println(u);
        } catch (SQLException a) {
            a.printStackTrace();
        }
        return u;
    }

    @Override
    public String login(Utilisateur user) {
        String message = "";
        try {
            String req = "SELECT * FROM `utilisateur` WHERE email=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            if (this.verifyEmailEx(user.getEmail())) {
                while (rs.next()) {
                    if (BCrypt.checkpw(user.getPassword(), rs.getString("password")) == true) {
                        message = "Connecté";
                        user = this.findByEmail(rs.getString("email"));
                        Session.setUser(user);
                    } else {
                        message = "Mot de passe Incorrect";
                    }
                }
            } else {
                if (this.isEmailValid(user.getEmail())) {
                    message = "wrong email format";
                } else {
                    message = "email introuvable";
                }
            }

        } catch (SQLException e) {
        }
        return message;
    }

    @Override
    public void logout() {
        Session.setUser(null);
    }

    @Override
    public boolean isConnected() {
        boolean isConnected = false;
        if (Session.getUser() != null) {
            isConnected = true;
            return isConnected;
        } else {
            return isConnected;
        }
    }

    @Override
    public boolean sendResetPasswordCode(String email) {
        boolean sent = false;
        if (this.verifyEmailEx(email)) {
            /* try {
                EmailSender.sendEmailWithAttachments(email, "RESET PASSWORD", "votre code : " + this.generateCode() + ", ce code est valide pour 30 minutes");
                sent = true;
            } catch (MessagingException ex) {
            }*/
        } else {
            System.out.println("mail doesn't exists");
            sent = false;
        }
        return sent;
    }

    @Override
    public boolean isUserBlocked(Utilisateur user) {
        boolean isBlocked = false;
        if (Session.getUser().getIs_blocked()) {
            isBlocked = true;
            System.err.println("Bloqué");
        }
        return isBlocked;
    }

    @Override
    public boolean verifyEmailEx(String email) {
        boolean check = false;
        if (this.isEmailValid(email)) {
            try {
                String req = "select *  from Utilisateur where email='" + email + "'";
                PreparedStatement us = cnx.prepareStatement(req);
                ResultSet result = us.executeQuery();

                while (result.next()) {
                    check = true;
                }

            } catch (SQLException e) {
            }
        } else {
            check = false;
        }
        return check;
    }

    @Override
    public String createAccount(Utilisateur user) {
        String message;

        if (this.verifyEmailEx(user.getEmail())) {
            message = "mail existant";
            System.out.println("mail existant");
        } else if (!this.isEmailValid(user.getEmail())) {
            message = "Mail format incorrect";
            System.out.println("Mail format incorrect");
        } else {

            String req = "INSERT INTO `utilisateur`(`nom`, `prenom`, `email`, `password`, `roles`, `langue`,`is_verified`, `is_blocked`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st;
            try {
                st = cnx.prepareStatement(req);

                st.setString(1, user.getNom());
                st.setString(2, user.getPrenom());
                st.setString(3, user.getEmail());
                st.setString(4, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
                st.setString(5, "[\"" + user.getRoles().toUpperCase() + "\"]");
                st.setString(6, user.getLangue());
                st.setBoolean(7, false);
                st.setBoolean(8, false);
                st.executeUpdate();

                System.out.println("Compte Créer avec success");
                message = "crée";
                //EmailSender.sendEmailWithAttachments(user.getEmail(), "BIENVENUE A TRAVEDIA", "Bienvenue à Travedia");
                Session.setUser(user);
            } catch (SQLException ex) {
                message = "sql error";
            }
        }
        return message;
    }

    @Override

    public void Accdelete() {
        String req = "delete from utilisateur where id='" + Session.getUser().getId() + "'";
        try {
            PreparedStatement us = cnx.prepareStatement(req);
            us.executeUpdate();
            System.out.println("Utilisateur Supprimé");
            this.logout();

        } catch (SQLException e) {
        }
    }

    @Override
    public boolean ModifierPassword(String oldPassword, String newPassword) {
        boolean isUpdated = false;
        if (BCrypt.checkpw(oldPassword, Session.getUser().getPassword())) {
            try {
                String passwordEnc = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                String req = "update utilisateur set password='" + passwordEnc + "' where id='" + Session.getUser().getId() + "'";

                PreparedStatement ps = cnx.prepareStatement(req);
                ps.executeUpdate();
                System.out.println("Mot de passe Modifié");
                isUpdated = true;
            } catch (SQLException e) {
            }
        } else {
            isUpdated = false;
            System.out.println("Mot de passe Incorrect");
        }
        return isUpdated;
    }

    @Override
    public boolean resetPassword(String email, String newPassword) {
        boolean isReset = false;
        try {
            String passwordEnc = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            String req = "update utilisateur set password='" + passwordEnc + "' where email='" + email + "'";

            PreparedStatement us = cnx.prepareStatement(req);
            us.executeUpdate();
            System.out.println("Nouveau Mot de passe enregistré");
            isReset = true;

        } catch (SQLException e) {
        }
        return isReset;
    }

    @Override
    public String checkRole(Utilisateur user) {
        String role = null;
        System.out.println(Session.getUser().getRoles());
        if (Session.getUser().getRoles().contains("ADMIN")) {
            role = "admin";
            System.out.println("admin");
            return role;
        }

        if (Session.getUser().getRoles().contains("VOYAGEUR")) {
            role = "voyageur";
            System.out.println("voyageur");
            return role;
        }

        if (Session.getUser().getRoles().contains("GUIDE")) {
            role = "Guide";
            System.out.println("guide");
            return role;
        }

        return role;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.UtilisateurService;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Ibtihel
 */
public class UserProfileController implements Initializable {

    @FXML
    private Label menueback;
    @FXML
    private Label menu;
    @FXML
    private AnchorPane slider;
    @FXML
    private Label menu1;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Label username;
    @FXML
    private Label userRole;
    @FXML
    private Label userprenom;
    @FXML
    private JFXButton logout;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.showProfile();
        // TODO
    }
    Stage profilStage = new Stage();
    UtilisateurService us = new UtilisateurService();

    @FXML
    private void edit() throws IOException {

        Parent roottoprofil = FXMLLoader.load(getClass().getResource("EditProfile.fxml"));
        Scene scene = new Scene(roottoprofil);
        profilStage.setTitle("Modifier Profile");
        profilStage.setScene(scene);
        profilStage.show();

    }

    @FXML
    private void delete() throws IOException {

        if (us.Accdelete()) {
            Parent rootsupp = FXMLLoader.load(getClass().getResource("user log.fxml"));
            Scene scene = new Scene(rootsupp);
            profilStage.setTitle("Supprimer Profile");
            profilStage.setScene(scene);
            profilStage.show();

        }
    }

    public void showProfile() {
        String prenom = Session.getUser().getPrenom();
        String nom = Session.getUser().getNom();
        String role = Session.getUser().getRoles();

        if (role.contains("ADMIN")) {
            role = "Admin";
        } else if (role.contains("VOYAGEUR")) {
            role = "Voyageur";

        } else if (role.contains("GUIDE")) {
            role = "Guide";

        }

        userprenom.setText("Nom : " + prenom);
        username.setText("Prenom : " + nom);
        userRole.setText("Role : " + role);
    }

    @FXML
    private void logout() throws IOException {
        us.logout();
        Parent rootlogout = FXMLLoader.load(getClass().getResource("user log.fxml"));
        Scene scene = new Scene(rootlogout);
        profilStage.setTitle("Modifier Profile");
        profilStage.setScene(scene);
        profilStage.show();

    }

}

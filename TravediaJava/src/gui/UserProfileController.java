/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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

    @FXML
    private void edit(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
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

}

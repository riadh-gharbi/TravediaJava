/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Utilisateur;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UtilisateurService;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Ibtihel
 */
public class EditProfileController implements Initializable {

    @FXML
    private Label menueback;
    @FXML
    private Label menu;
    @FXML
    private Button confirmer;
    @FXML
    private Button annuler;
    @FXML
    private TextField editnom;
    @FXML
    private TextField editprenom;
    @FXML
    private TextField editemail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        editnom.setPromptText(Session.getUser().getNom());
        editprenom.setPromptText(Session.getUser().getPrenom());
        editemail.setPromptText(Session.getUser().getEmail());
        //editrolebox.setPromptText(Session.getUser().getRoles());
        //editlanguebox.setPromptText(Session.getUser().getLangue());

    }

    @FXML
    private void confirmerEdit() throws IOException {

        String email = editemail.getText();
        String nom = editnom.getText();
        String prenom = editprenom.getText();

        int error = 0;

        if (email.isEmpty()) {
            editemail.setStyle("-fx-border-color: red; -fx-border-width: 1px");
            error++;
        } else {
            editemail.setStyle(null);
        }

        if (nom.isEmpty()) {
            editnom.setStyle("-fx-border-color: red; -fx-border-width: 1px");
            error++;
        } else {
            editnom.setStyle(null);
        }

        if (prenom.isEmpty()) {
            editprenom.setStyle("-fx-border-color: red; -fx-border-width: 1px");
            error++;
        } else {
            editprenom.setStyle(null);
        }

        Stage profilStage = new Stage();

        Utilisateur u = Session.getUser();

        if (error == 0) {

            u.setNom(editnom.getText());
            u.setPrenom(editprenom.getText());
            u.setEmail(editemail.getText());
            UtilisateurService rs = new UtilisateurService();

            rs.modifier(u);

            Parent roottoprofil = FXMLLoader.load(getClass().getResource("UserProfile.fxml"));
            Scene scene = new Scene(roottoprofil);
            profilStage.setTitle("Profile");
            profilStage.setScene(scene);
            profilStage.show();

        }

    }

    @FXML
    private void retourProfile() {
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Profile;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Stage;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Ibtihel
 */
public class AdminCreateAccountController implements Initializable {

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button Adminsignup;

    @FXML
    private Button Adminsignin;

    Utilisateur toAdmin = new Utilisateur();
    Profile Adminprofile = new Profile();
    UtilisateurService as = new UtilisateurService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void login(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("user log.fxml"));
            Adminsignin.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void AdmincreateAccount(ActionEvent event) {

        int error = 0;

        if (email.getText().isEmpty()) {
            email.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            error++;
        } else {
            email.setStyle(null);
        }

        if (password.getText().isEmpty()) {
            password.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            error++;

        } else {
            password.setStyle(null);
        }

        if (nom.getText().isEmpty()) {
            nom.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            error++;
        } else {
            nom.setStyle(null);
        }

        if (prenom.getText().isEmpty()) {
            prenom.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            error++;
        } else {
            prenom.setStyle(null);
        }

        if (error == 0) {

            toAdmin.setNom(nom.getText());
            toAdmin.setPrenom(prenom.getText());
            toAdmin.setEmail(email.getText());
            toAdmin.setPassword(password.getText());
            toAdmin.setRoles("\"ROLE_ADMIN\"");

            Stage profilStage = new Stage();
            switch (as.AdminCreateAccount(toAdmin, Adminprofile)) {

                case "admin mail existant":
                    BoxBlur blur = new BoxBlur(3, 3, 3);
                    email.setStyle("-fx-border-color: red; -fx-border-width: 2px");
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setHeaderText(null);
                    a.setContentText("email existant");
                    a.showAndWait();

                    break;
                case "admincrée":
                    //us.addProfile();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                        Adminsignup.getScene().setRoot(root);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                    break;
                case "admin Mail format incorrect":
                    BoxBlur b = new BoxBlur(3, 3, 3);
                    email.setStyle("-fx-border-color: red; -fx-border-width: 2px");
                    Alert alrt = new Alert(Alert.AlertType.ERROR);
                    alrt.setHeaderText(null);
                    alrt.setContentText("format email incorrect");
                    alrt.showAndWait();

                    break;
                case "admin sql error":
                    BoxBlur b2 = new BoxBlur(3, 3, 3);
                    email.setStyle("-fx-border-color: red; -fx-border-width: 2px");
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setHeaderText(null);
                    al.setContentText("erreur base de données");
                    al.showAndWait();
                    break;
                default:
                    break;
            }

        }
    }
}

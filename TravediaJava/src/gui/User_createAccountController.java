/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class User_createAccountController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox rolebox;
    @FXML
    private ComboBox languebox;
    @FXML
    private Button signin;
    @FXML
    private Button signup;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rolebox.getItems().addAll(
                "Voyageur",
                "Guide"
        );
        languebox.getItems().addAll(
                "Arabe",
                "Anglais",
                "Français"
        );
    }

    Utilisateur toSignUp = new Utilisateur();
    UtilisateurService us = new UtilisateurService();

    @FXML
    public void createAccount() throws IOException, SQLException {

        String role = "";
        String langue = "";

        int error = 0;

        if (rolebox.getValue() == null) {
            rolebox.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            error++;
        } else {
            rolebox.setStyle(null);
        }

        if (languebox.getValue() == null) {
            languebox.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            error++;
        } else {
            languebox
                    .setStyle(null);
        }

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
            String selectedRole = rolebox.getValue().toString();
            String selectedLangue = languebox.getValue().toString();
            if (selectedRole.equals("Voyageur")) {
                role = "ROLE_VOYAGEUR";
            } else if (selectedRole.equals("Guide")) {
                role = "ROLE_GUIDE";
            }
            if (selectedLangue.equals("Arabe")) {
                langue = "arabe";
            } else if (selectedLangue.equals("Anglais")) {
                langue = "anglais";
            } else if (selectedLangue.equals("Français")) {
                langue = "français";
            }
            toSignUp.setEmail(email.getText());
            toSignUp.setNom(nom.getText());
            toSignUp.setRoles(role);
            toSignUp.setPrenom(prenom.getText());
            toSignUp.setPassword(password.getText());
            toSignUp.setLangue(langue);

            Stage profilStage = new Stage();
            switch (us.createAccount(toSignUp)) {

                case "mail existant":
                    BoxBlur blur = new BoxBlur(3, 3, 3);
                    email.setStyle("-fx-border-color: red; -fx-border-width: 2px");
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setHeaderText(null);
                    a.setContentText("email existant");
                    a.showAndWait();

                    break;
                case "crée":
                    //us.addProfile();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("UserProfile.fxml"));
                        signup.getScene().setRoot(root);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    /* Parent roottocompte = FXMLLoader.load(getClass().getResource("UserProfile.fxml"));
                    Scene scene = new Scene(roottocompte);
                    profilStage.setTitle("Profile");
                    profilStage.setScene(scene);
                    profilStage.show();
                    //primaryStage.close();*/

                    break;
                case "Mail format incorrect":
                    BoxBlur b = new BoxBlur(3, 3, 3);
                    email.setStyle("-fx-border-color: red; -fx-border-width: 2px");
                    Alert alrt = new Alert(Alert.AlertType.ERROR);
                    alrt.setHeaderText(null);
                    alrt.setContentText("format email incorrect");
                    alrt.showAndWait();

                    break;
                case "sql error":
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

    @FXML
    private void login() throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("user log.fxml"));
            signin.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXDialogLayout;
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
import services.UtilisateurService;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Ibtihel
 */
public class UserLogController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button forgotpass;
    @FXML
    private Button addacount;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //add alerts here
    @FXML
    public void login(ActionEvent event) throws IOException {
        Utilisateur u = new Utilisateur();
        UtilisateurService us = new UtilisateurService();

        int error = 0;

        if (email.getText().isEmpty()) {
            email.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez ajouter un email ! ");
            al.showAndWait();
            error++;
        } else {
            email.setStyle(null);
        }

        if (password.getText().isEmpty()) {
            password.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez ajouter un mot de passe! ");
            al.showAndWait();
            error++;
        } else {
            password.setStyle(null);
        }

        if (error == 0) {
            u.setEmail(email.getText());
            u.setPassword(password.getText());
            switch (us.login(u)) {
                case "Mot de passe Incorrect":
                    BoxBlur blur = new BoxBlur(3, 3, 3);
                    password.setStyle("-fx-border-color: red; -fx-border-width: 2px");
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setHeaderText(null);
                    al.setContentText("Mot de passe incorrect");
                    al.showAndWait();

                    break;
                case "email introuvable":
                    blur = new BoxBlur(3, 3, 3);
                    password.setStyle("-fx-border-color: red; -fx-border-width: 2px");
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setHeaderText(null);
                    a.setContentText("email introuvable");
                    a.showAndWait();

                    break;
                case "wrong mail format":
                    blur = new BoxBlur(3, 3, 3);
                    password.setStyle("-fx-border-color: red; -fx-border-width: 2px");
                    Alert a1 = new Alert(Alert.AlertType.ERROR);
                    a1.setHeaderText(null);
                    a1.setContentText("format email non valide");
                    a1.showAndWait();

                    break;
                case "logged in":
                    if (Session.getUser().getIs_blocked()) {

                        blur = new BoxBlur(3, 3, 3);
                        password.setStyle("-fx-border-color: red; -fx-border-width: 2px");
                        Alert a2 = new Alert(Alert.AlertType.ERROR);
                        a2.setHeaderText(null);
                        a2.setContentText("Votre compte est bloqué");
                        a2.showAndWait();

                        us.logout();
                    } else {
                        System.out.println("Success yeeeeyyy");
                    }
                    break;
                default:
                    break;
            }
        }

    }

    @FXML
    private void forgotPassword(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));
            forgotpass.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void createAccount() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("user_createAccount.fxml"));
            addacount.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}

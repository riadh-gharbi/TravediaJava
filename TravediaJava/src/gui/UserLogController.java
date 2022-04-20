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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //user toLogin = new user();
    // userInterface us = new userServices();
    //add alerts here
    @FXML
    public void login(ActionEvent event) throws IOException {
        Utilisateur u = new Utilisateur();

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

        /* if (email.getText().isEmpty() | password.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
        }*/
 /* else
        /*{

            switch (us.login(u)) {
                case "wrong password":
                    System.err.println("password");
                    break;
                case "no user with such email":
                    System.err.println("no user");
                    break;
                case "wrong mail format":
                    System.err.println("wrong email format");
                    break;
                case "logged in":
                    if (Session.getUser().getIs_blocked()) {
                        System.err.println("Vous etes bloqué");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Votre compte est bloqué");
                        alert.show();
                        us.logout();
                    } else {
                        System.out.println("Bienvenue!");
                        this.goToProfilePage();
                    }
                    break;
                default:
                    break;
            }
        }*/
    }

    //add alerts here
    @FXML
    private void forgotPassword(ActionEvent event) {
    }

    @FXML
    private void createAccount(ActionEvent event) {
    }
}

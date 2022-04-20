/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Ibtihel
 */
public class ResetPasswordController implements Initializable {

    @FXML
    private Button retourlogin;
    @FXML
    private Button confirmationpage;
    @FXML
    private TextField emailField;

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
    UtilisateurService us = new UtilisateurService();

    @FXML
    private void envoyer(ActionEvent event) {
    }

    @FXML
    private void cancel(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("user log.fxml"));
            retourlogin.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sendResetPasswordCode() {
        String email = emailField.getText();
        int error = 0;
        if (email.isEmpty()) {
            emailField.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            error++;
        } else {
            emailField.setStyle(null);
        }
        if (error == 0) {
            us.sendResetPasswordCode(email);
            BoxBlur blur = new BoxBlur(3, 3, 3);
            emailField.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Code envoyé");
            al.showAndWait();

        }

    }
}

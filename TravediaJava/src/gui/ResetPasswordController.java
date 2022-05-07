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

    private static String emailReset;

    UtilisateurService us = new UtilisateurService();

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

    @FXML
    private void cancel(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("user log.fxml"));
            retourlogin.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void envoyer() {
        emailReset = emailField.getText();
        int error = 0;
        if (emailReset.isEmpty()) {
            emailField.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Entrer votre email svp");
            al.showAndWait();
            error++;
        } else {
            emailField.setStyle(null);
        }

        if (error == 0) {
            if (us.verifyEmailEx(emailReset) == true) {

                us.sendResetPasswordCode(emailReset);
                BoxBlur blur1 = new BoxBlur(3, 3, 3);
                Alert a1 = new Alert(Alert.AlertType.ERROR);
                a1.setHeaderText(null);
                a1.setContentText("Code envoyé");
                a1.showAndWait();

                try {
                    Parent root = FXMLLoader.load(getClass().getResource("code_password.fxml"));
                    root.setUserData(emailReset);
                    confirmationpage.getScene().setRoot(root);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            } else {

                System.err.println("error");

                BoxBlur blur = new BoxBlur(3, 3, 3);
                emailField.setStyle("-fx-border-color: red; -fx-border-width: 2px");
                Alert a2 = new Alert(Alert.AlertType.ERROR);
                a2.setHeaderText(null);
                a2.setContentText("Email Inexistant");
                a2.showAndWait();
            }
        }
    }
}

/* emailField.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Code envoyé");
            al.showAndWait();

        }

    }
}*/

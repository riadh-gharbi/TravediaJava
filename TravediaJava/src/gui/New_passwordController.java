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

/**
 * FXML Controller class
 *
 * @author Ibtihel
 */
public class New_passwordController implements Initializable {

    @FXML
    private Button backtologin;
    @FXML
    private Button redlogin;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmationpassField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //add alerts here
    public void updatePassword() throws IOException {
        String password = passwordField.getText();
        String confirmPassword = confirmationpassField.getText();
        int error = 0;

        if (password.isEmpty()) {
            passwordField.setStyle("-fx-border-color: red; -fx-border-width: 1px");
            error++;
        } else {
            passwordField.setStyle(null);
        }

        if (confirmPassword.isEmpty()) {
            confirmationpassField.setStyle("-fx-border-color: red; -fx-border-width: 1px");
            error++;
        } else {
            confirmationpassField.setStyle(null);
        }

        if (!password.equals(confirmPassword)) {
            BoxBlur blur = new BoxBlur(3, 3, 3);
            passwordField.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            Alert a1 = new Alert(Alert.AlertType.ERROR);
            a1.setHeaderText(null);
            a1.setContentText("Mot de passe non identique");
            a1.showAndWait();

            error++;
        }

        if (error == 0) {
            /*us.resetPassword(emailReset, password);
            this.goToLoginPage();*/
        }
    }

    @FXML
    private void confirmer(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("user log.fxml"));
            backtologin.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void annuler(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("user log.fxml"));
            redlogin.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

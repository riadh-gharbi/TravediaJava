/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Ibtihel
 */
public class Code_passwordController implements Initializable {

    @FXML
    private TextField codepas;
    UtilisateurService us = new UtilisateurService();
    @FXML
    private Button envcode;
    @FXML
    private Button annulcode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void envoyer() throws IOException {
        String codeStr = codepas.getText();
        int code = Integer.parseInt(codeStr);

        int error = 0;
        if (codeStr.isEmpty()) {
            codepas.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            //new animatefx.animation.Shake(codepas).play();
            error++;

        } else {
            codepas.setStyle(null);
        }
        if (error == 0) {
            us.isCodeValid(code);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("new_password.fxml"));
                envcode.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            System.err.println("error");
        }
        BoxBlur blur = new BoxBlur(3, 3, 3);

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Label("Ce code est incorrect"));
        JFXButton button = new JFXButton("OK");
        button.setPrefSize(120, 50);
        button.setStyle("-fx-font-size:14;-fx-text-fill:white;-fx-background-color:#000000; -fx-background-radius:20px;-fx-border-radius:20px");

    }

    @FXML
    private void annuler(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));
            annulcode.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

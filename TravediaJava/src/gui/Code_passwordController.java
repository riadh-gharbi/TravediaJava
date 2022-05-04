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

    /*
    public void validateCode() throws IOException {
        String codeStr = resetPasswordCodeField.getText();
        int code = Integer.parseInt(codeStr);

        int error = 0;
        if (codeStr.isEmpty()) {
            resetPasswordCodeField.setStyle("-fx-border-color: red; -fx-border-width: 1px");
            new animatefx.animation.Shake(resetPasswordCodeField).play();
            error++;
        } else if (LocalDateTime.now().isAfter(EnterEmailToResetController.expiryDate)) {
            BoxBlur blur = new BoxBlur(3, 3, 3);

            JFXDialogLayout dialogLayout = new JFXDialogLayout();
            dialogLayout.setHeading(new Label("Ce code est expiré"));
            JFXButton button = new JFXButton("OK");
            button.setPrefSize(120, 50);
            button.setStyle("-fx-font-size:14;-fx-text-fill:white;-fx-background-color:#000000; -fx-background-radius:20px;-fx-border-radius:20px");
            dialogLayout.setActions(button);
            JFXDialog dialog = new JFXDialog(rootPaneEmail, dialogLayout, JFXDialog.DialogTransition.TOP);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                anchorEmail.setEffect(null);
                dialog.close();
            });
            dialog.show();
            dialog.setOnDialogClosed((JFXDialogEvent event) -> {
                anchorEmail.setEffect(null);
            });
            anchorEmail.setEffect(blur);
            error++;
        } else {
            resetPasswordCodeField.setStyle(null);
        }
        if (error == 0) {
            us.isCodeValid(code);
            Parent root = FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));
            Main.primaryStage.setScene(new Scene(root));
            Main.primaryStage.show();
            Main.primaryStage.setFullScreen(true);
        } else {
            System.err.println("error");
        }
        BoxBlur blur = new BoxBlur(3, 3, 3);

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Label("Ce code est incorrect"));
        JFXButton button = new JFXButton("OK");
        button.setPrefSize(120, 50);
        button.setStyle("-fx-font-size:14;-fx-text-fill:white;-fx-background-color:#000000; -fx-background-radius:20px;-fx-border-radius:20px");
        dialogLayout.setActions(button);
        JFXDialog dialog = new JFXDialog(rootPaneEmail, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            anchorEmail.setEffect(null);
            dialog.close();
        });
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event) -> {
            anchorEmail.setEffect(null);
        });
        anchorEmail.setEffect(blur);
    }
     */
    @FXML
    public void envoyer() throws IOException {
        String codeStr = codepas.getText();
        int code = Integer.parseInt(codeStr);

        int error = 0;
        if (codeStr.isEmpty()) {
            codepas.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            //new animatefx.animation.Shake(codepas).play();
            Alert a1 = new Alert(Alert.AlertType.ERROR);
            a1.setHeaderText(null);
            a1.setContentText("Saisir votre code");
            a1.showAndWait();

            error++;

        } else {
            codepas.setStyle(null);
        }
        if (error == 0) {
            /* switch (us.isCodeValid(u)) {
                            case "admin":
                                try {
                                    Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                                    loginbtn.getScene().setRoot(root);
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }

                                //primaryStage.close();
                                break;
                            case "voyageur":
                                try {
                                    Parent root = FXMLLoader.load(getClass().getResource("UserProfile.fxml"));
                                    loginbtn.getScene().setRoot(root);
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }

                                break;*/
            if (us.isCodeValid(code) == true) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("new_password.fxml"));
                    envcode.getScene().setRoot(root);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            } else {
                if (us.isCodeValid(code) == false) {
                    System.err.println("error");

                    BoxBlur blur = new BoxBlur(3, 3, 3);
                    codepas.setStyle("-fx-border-color: red; -fx-border-width: 2px");
                    Alert a2 = new Alert(Alert.AlertType.ERROR);
                    a2.setHeaderText(null);
                    a2.setContentText("Ce code est incorrect");
                    a2.showAndWait();
                }
            }
        }
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

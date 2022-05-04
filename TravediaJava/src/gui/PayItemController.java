/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class PayItemController implements Initializable {

    @FXML
    private Label client;
    @FXML
    private Label guide;
    @FXML
    private Label prix;
    @FXML
    private Label etat;
    @FXML
    private Label type;
    @FXML
    private Label stripe;
    @FXML
    private JFXButton modify;
    @FXML
    private JFXButton delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editRec(ActionEvent event) {
    }

    @FXML
    private void deleteRec(ActionEvent event) {
    }
    
}

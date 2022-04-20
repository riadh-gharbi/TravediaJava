/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Destination;
import entities.Region;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class ItemDestinationController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label desc;
    @FXML
    private Button editbtn;
    @FXML
    private Button deletebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setRegion(Destination r){
        nom.setText(r.getNom());
        desc.setText(r.getDescription());
}
}
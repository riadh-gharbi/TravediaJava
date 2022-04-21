/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import entities.Hotel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Razer
 */
public class HotelItemController implements Initializable {

    @FXML
    private JFXButton modify;
    @FXML
    private JFXButton delete;
    @FXML
    private Label nom;
    @FXML
    private Label adresse;
    @FXML
    private Label email;
    @FXML
    private Label numtel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void  setHotels(Hotel h) {
    nom.setText(h.getNom());
    adresse.setText(h.getAdresse());
    email.setText(h.getEmail());
    numtel.setText(String.valueOf(h.getNumTel()));


} 
    
}

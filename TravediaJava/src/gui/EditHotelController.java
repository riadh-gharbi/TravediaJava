/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Hotel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import services.HotelService;

/**
 * FXML Controller class
 *
 * @author Razer
 */
public class EditHotelController implements Initializable {

    @FXML
    private TextField hnom;
    @FXML
    private TextField hadresse;
    @FXML
    private TextField hemail;
    @FXML
    private TextField hnumtel;
    Hotel currenthotel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ListeHotel(ActionEvent event) {
    }
     

    @FXML
    private void edithotel(ActionEvent event) throws IOException {
         currenthotel.setNom(hnom.getText());
        currenthotel.setAdresse(hadresse.getText());
        currenthotel.setEmail(hemail.getText());
        currenthotel.setNumTel(Integer.parseInt(hnumtel.getText()));
        HotelService catser = new HotelService();
        catser.modifier(currenthotel);
        Parent root = FXMLLoader.load(getClass().getResource("AfficherHotel.fxml"));
        hnom.getScene().setRoot(root);
    
    }
    public void setHotel(Hotel ho){
       
        currenthotel = ho;
        hnom.setText(ho.getNom());
        hadresse.setText(ho.getAdresse());
        hemail.setText(ho.getEmail());
        hnumtel.setText(String.valueOf(ho.getNumTel()));
        
    }
    
    
}

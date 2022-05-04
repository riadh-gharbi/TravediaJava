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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.HotelService;

/**
 * FXML Controller class
 *
 * @author Razer
 */
public class AjouterHotelController implements Initializable {

    @FXML
    private TextField hnom;
    @FXML
    private TextField hadresse;
    @FXML
    private TextField hemail;
    @FXML
    private TextField hnumtel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    @FXML
    private void AjouterHotel(ActionEvent event) {
        Hotel h = new Hotel();
        h.setNom(hnom.getText());
        h.setAdresse(hadresse.getText());
        h.setEmail(hemail.getText());
        h.setNumTel(Integer.parseInt(hnumtel.getText()));
        HotelService hs = new  HotelService();
        hs.ajouter(h);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SUCCESS");
        alert.setContentText("Hotel Added");
        alert.show();
        //../hui/affichage (to access a fxml file if not same package)
        //Parent root = FXMLLoader.load(getClass().getResource("Hotel.fxml"));
        // hnom.getScene().setRoot(root); //recupere scecne courante et remplacer avec une autre scene
        
    }

    @FXML
    private void ListeHotel(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherHotel.fxml"));
        Parent root = loader.load(); 
        hnom.getScene().setRoot(root);
    }
    }
    


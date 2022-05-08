/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXTextField;
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
import services.HotelService;

/**
 * FXML Controller class
 *
 * @author Razer
 */
public class HotelController implements Initializable {

    @FXML
    private JFXTextField hnom;
    @FXML
    private JFXTextField hadresse;
    @FXML
    private JFXTextField hemail;
    @FXML
    private JFXTextField hnumtel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addHotel(ActionEvent event) {
         try {
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
            Parent root = FXMLLoader.load(getClass().getResource("Hotel.fxml"));
            hnom.getScene().setRoot(root); //recupere scecne courante et remplacer avec une autre scene
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    }
    


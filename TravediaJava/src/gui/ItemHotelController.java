/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Hotel;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import services.HotelService;

/**
 * FXML Controller class
 *
 * @author Razer
 */
public class ItemHotelController implements Initializable {

    @FXML
    private Label hnom;
    @FXML
    private Label hadresse;
    @FXML
    private Label hemail;
    @FXML
    private Label hnumtel;
    AfficherHotelController controller;
    Hotel hotel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
       public void setHotel(Hotel h){
        
        hotel =h; 
        hnom.setText(h.getNom());
        hadresse.setText(h.getAdresse());
        hemail.setText(h.getEmail());
        hnumtel.setText(String.valueOf(h.getNumTel()));
               
        
    }
      public void setParentController(AfficherHotelController contr)
    {
        controller = contr;
    }

    @FXML
    private void edit(ActionEvent event) {
        try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("EditHotel.fxml"));
            Parent root = loader.load();
            EditHotelController editho = loader.getController();
            editho.setHotel(hotel);
           hnom.getScene().setRoot(root);
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @FXML
    private void delete(ActionEvent event) {
   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Hotel");
        alert.setHeaderText("Attention , Vous allez supprimer un Hotel!");
        alert.setContentText("Voulez vous vraiment supprimer cet Hotel?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            HotelService ps = new HotelService();
            ps.supprimer(hotel.getId());
            controller.refreshList();
        }
    }}
    


    
   
     

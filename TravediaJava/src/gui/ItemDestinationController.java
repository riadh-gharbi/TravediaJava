/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Destination;
import entities.Region;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.util.Duration;
import services.DestinationService;
import services.RegionService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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

  Destination currentDestination;
  
    AfficherDestinationController controller;
    @FXML
    private Label regionom;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setParentController(AfficherDestinationController contr)
    {
        controller = contr;
    }
       public void setDestination(Destination r){
        currentDestination = r;
        nom.setText(r.getNom());
        desc.setText(r.getDescription());
        //regionom.setText(region1);
    }
     @FXML
    private void edit(ActionEvent event) throws IOException {
        try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierDestination.fxml"));
            Parent root = loader.load();
            ModifierDestinationController editc = loader.getController();
            editc.setDestination(currentDestination);
           nom.getScene().setRoot(root);
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @FXML
    private void delete(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Delete  Destination" );
alert.setHeaderText("We are about de delete "+ currentDestination.getNom());
alert.setContentText("Are you sure you want to delete it ?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    // ... user chose OK
     DestinationService rs= new DestinationService();
rs.supprimer(currentDestination.getId());
 TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            
            tray.setAnimationType(type);
            tray.setTitle("Well..");
            tray.setMessage("La destination a été supprimée");
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(3000));
Parent root = FXMLLoader.load(getClass().getResource("AfficherDestination.fxml"));
        nom.getScene().setRoot(root);
} else {
    System.out.println("cansle delete");
    // ... user chose CANCEL or closed the dialog
}
}
}
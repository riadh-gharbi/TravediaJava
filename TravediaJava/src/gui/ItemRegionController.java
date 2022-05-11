/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Region;
import java.io.File;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import services.RegionService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class ItemRegionController implements Initializable {


    @FXML
    private Label nom;
    @FXML
    private ImageView imagev;
    @FXML
    private Button edit;
    @FXML
    private Button delete;

  
   

  Region currentRegion;
    AfficherRegionController controller;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }  
    public void setParentController(AfficherRegionController contr)
    {
        controller = contr;
    }
       public void setRegion(Region r){
        currentRegion = r;
        nom.setText(r.getNom());
      //  imagev.setImage(value);
    }
     @FXML
    private void edit(ActionEvent event) throws IOException {
        try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierRegionSimple.fxml"));
            Parent root = loader.load();
            ModifierRegionController editc = loader.getController();
            editc.setRegion(currentRegion);
            FXMain.instance.getBackController().getAnchor().getChildren().clear();
            FXMain.instance.getBackController().getAnchor().getChildren().add(root);
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @FXML
    private void delete(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Delete  Region" );
alert.setHeaderText("We are about de delete "+ currentRegion.getNom());
alert.setContentText("Are you sure you want to delete it ?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    // ... user chose OK
     RegionService rs= new RegionService();
rs.supprimer(currentRegion.getId());
TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            
            tray.setAnimationType(type);
            tray.setTitle("Well..");
            tray.setMessage("La region a été supprimée");
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(3000));
//Parent root = FXMLLoader.load(getClass().getResource("ListRegionBackSimple.fxml"));
//        nom.getScene().setRoot(root);
            FXMain.instance.getBackController().render("ListRegionBackSimple.fxml");
} else {
    System.out.println("cansle delete");
    // ... user chose CANCEL or closed the dialog
}
//        RegionService rs= new RegionService();
//rs.supprimer(currentRegion.getId());
//Parent root = FXMLLoader.load(getClass().getResource("AfficherRegion.fxml"));
//        nom.getScene().setRoot(root);
    }
        
    }



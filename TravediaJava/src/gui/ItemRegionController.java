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
import services.RegionService;

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
    }
     @FXML
    private void edit(ActionEvent event) throws IOException {
        try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierRegion.fxml"));
            Parent root = loader.load();
            ModifierRegionController editc = loader.getController();
            editc.setRegion(currentRegion);
           nom.getScene().setRoot(root);
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
Parent root = FXMLLoader.load(getClass().getResource("AfficherRegion.fxml"));
        nom.getScene().setRoot(root);
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



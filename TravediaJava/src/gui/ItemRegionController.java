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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

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

  
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setRegion(Region r){
        nom.setText(r.getNom());
         //       fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif","*.jpeg"));

//        selectedFile = fc.showOpenDialog(null);
//        if (selectedFile != null) {

         //   path = selectedFile.getName();
//    
           // imagepath.setText(path);
          //  Image imagea = new Image(selectedFile.toURI().toString());
    //       imagev.getImage(r.getImage().toURI().toString());
//imagev.setText(r.getImage());
//path = r.getImage().toURI().toString();
//imagev.setImage(r.getImage().toURI().toString());
    }
       // imagev.setImage(selectedFile.toURI().toString());
     @FXML
    private void edit(ActionEvent event) throws IOException {
         
          Region r = new Region();
        r.setNom(nom.getText());
        //  Region p= tablev.getSelectionModel().getSelectedItem();
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierRegion.fxml"));
        Parent root =loader.load();
        ModifierRegionController dc = loader.getController();
        dc.initDon(r);
        edit.getScene().setRoot(root);
    }

    @FXML
    private void delete(ActionEvent event) {
    }
        
    }



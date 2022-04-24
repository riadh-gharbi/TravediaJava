/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import entities.Evenement;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FrontItemEvController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    @FXML
    private Label datefin;
    @FXML
    private Label datedeb;
    @FXML
    private Label description;
    FrontController controller;
    Evenement currentEvent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    public void setEvenement(Evenement ev){
         currentEvent = ev;
         nom.setText(ev.getNom());
        description.setText(ev.getDescription());
        datedeb.setText(ev.getDatedeb().toString());
        datefin.setText(ev.getDatefin().toString());
        
        //image.setText(ev.getImage());
       

        
        File imageFile = new File(AddEventController.imageDir + "/" + ev.getImage());
        Image imagea = new Image(imageFile.toURI().toString());
        image.setImage(imagea) ;
        
    }
    
}

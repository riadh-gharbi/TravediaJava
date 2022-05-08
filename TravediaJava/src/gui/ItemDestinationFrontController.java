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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class ItemDestinationFrontController implements Initializable {

    @FXML
    private VBox vboxitmd;
    @FXML
    private HBox hboxitemd;
    @FXML
    private Label nomdest;
 private Destination destination = null;
 Destination currentDest;
    FrontController controller;
    @FXML
    private ImageView imgd;
    @FXML
    private Hyperlink details;
    
    
   // Destination currentDestination;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setParentController(FrontController contr)
    {
        controller = contr;
    }
     void setData(Destination r) {
        currentDest = r;
        nomdest.setText(r.getNom());
}

    @FXML
    private void detais(ActionEvent event) throws IOException {
    //   Destination p= new Destination();
               //  Personne p= tablev.getSelectionModel().getSelectedItem();
               controller.detailsDest(currentDest);
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsDestinationSimple.fxml"));
//        Parent root =loader.load();
//        DetailsDestinationController dc = loader.getController();
//        dc.setDestination(currentDest);
        
        //details.getScene().setRoot(root);
    }
    }



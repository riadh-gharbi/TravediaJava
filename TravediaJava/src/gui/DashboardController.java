/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class DashboardController implements Initializable {
    private Parent test;
    //private BorderPane Center;
    @FXML
    private AnchorPane anchor;

    public AnchorPane getAnchor() {
        return anchor;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Reclamationlist(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("recListBack.fxml"));
            test = loader.load();
            
            //if (Center.getCenter() == null)
            anchor.getChildren().clear();
            anchor.getChildren().add(test);
             AnchorPane.setTopAnchor(test,0.0);
                AnchorPane.setBottomAnchor(test,0.0);
                AnchorPane.setRightAnchor(test, -10.0);
                AnchorPane.setLeftAnchor(test, 5.0);
        } catch (IOException ex) {
            System.err.println("Error List Rec "+ ex.getMessage() + " "+ ex.getCause() + " " + Arrays.toString(ex.getSuppressed()));
        }
    }

    @FXML
    private void PaiementList(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("listPayBack.fxml"));
            test = loader.load();
            
            //if (Center.getCenter() == null)
            anchor.getChildren().clear();
            anchor.getChildren().add(test);
            AnchorPane.setTopAnchor(test,0.0);
            AnchorPane.setBottomAnchor(test,0.0);
            AnchorPane.setRightAnchor(test, -10.0);
            AnchorPane.setLeftAnchor(test, 5.0);
        } catch (IOException ex) {
            System.err.println("Error List Pay "+ ex.getMessage() + " "+ ex.getCause() + " " + ex.getStackTrace().toString());
        }
    }
    
}

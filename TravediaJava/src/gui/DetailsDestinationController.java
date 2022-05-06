/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Destination;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class DetailsDestinationController implements Initializable {

    @FXML
    private Label menueback;
    @FXML
    private Label menu;
    @FXML
    private Label menu1;
    @FXML
    private Label nom;
    @FXML
    private Label desc;
    @FXML
    private WebView mapview;
    private WebEngine webengine ; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          webengine=mapview.getEngine();
        url=this.getClass().getResource("map/map/index.html");
        webengine.load(url.toString());
    }    
     public void setDestination(Destination p){
       nom.setText(p.getNom());
       desc.setText(p.getDescription());
    }
}

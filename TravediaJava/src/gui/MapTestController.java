/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class MapTestController implements Initializable {

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
  
    @FXML
    private void btn(ActionEvent event) {
        Double latitude =(Double) mapview.getEngine().executeScript("lat");
        Double longitude =(Double) mapview.getEngine().executeScript("lon");
        
        System.out.println("latajout"+latitude);
        System.out.println("longjout"+longitude);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class WeatherController implements Initializable {

    @FXML
    private Label menueback;
    @FXML
    private Label menu;
    @FXML
    private VBox slider;
    @FXML
    private Label menu1;
   
    @FXML
    private ImageView icon;
    @FXML
    private Label temperature;
    @FXML
    private Label humidity;
    @FXML
    private Label desc;
    @FXML
    private Label pression;
    CategorieService rec = new CategorieService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            //   String[] test =
            String[] testa = new CategorieService().getData();
             temperature.setText(testa[0]+"°");
             humidity.setText(testa[1]+"%");
             desc.setText(testa[2]);
             pression.setText(testa[3]+"hPa");
 
        } catch (ProtocolException ex) {
            Logger.getLogger(WeatherController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WeatherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

   
    
}

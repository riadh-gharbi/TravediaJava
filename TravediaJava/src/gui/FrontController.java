/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.animation.TranslateTransition;  
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FrontController implements Initializable {

    @FXML
    private Label menueback;
    @FXML
    private Label menu;
    @FXML
    private AnchorPane slider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        slider.setTranslateX(-210);
        menu.setOnMouseClicked(event->{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();
            
            slider.setTranslateX(-210);
            
            slide.setOnFinished((e)->{
                menu.setVisible(false);
                menueback.setVisible(true);
            });
        });
        
        slider.setTranslateX(-210);
        menueback.setOnMouseClicked(event->{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(-210);
            slide.play();
            
            slider.setTranslateX(0);
            
            slide.setOnFinished((e)->{
                menueback.setVisible(true);
                menu.setVisible(false);
            });
        });
    }    
    
}

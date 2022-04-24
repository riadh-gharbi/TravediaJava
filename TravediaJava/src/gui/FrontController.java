/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.animation.TranslateTransition;  
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import services.EvenementService;

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
    private VBox slider;
    @FXML
    private Label menu1;
    private GridPane grid;
    private List<Evenement> evenements;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        slider.setTranslateX(200);
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
                menu.setVisible(true);
                menueback.setVisible(false);
            });
        });
        
        EvenementService evser = new EvenementService();
        List<Evenement> evenements = evser.recuperer();
        int column = 0;
        int row = 1;
        try{
            for (Evenement event : evenements){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FrontItemEv.fxml"));
                Parent pane = fxmlLoader.load();
                FrontItemEvController evController= fxmlLoader.getController();
                evController.setEvenement(event);
                if (column == 2){
                    column = 0;
                    ++row;
                }
                grid.add(pane, column++, row);
                grid.setMargin(pane, new Insets(20));
            }
                
        }catch(IOException e){e.printStackTrace();};
        
    }    
    
}

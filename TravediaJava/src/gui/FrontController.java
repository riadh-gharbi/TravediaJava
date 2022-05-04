/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.animation.TranslateTransition;  
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private Label menu1;
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXButton pay;
    @FXML
    private JFXButton rec;
    
    public AnchorPane GetSlider()
    {
        return slider;
    }
    public AnchorPane getAnchorPane()
    {
        return this.anchor;
    }
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        slider.setTranslateX(-210);
        menu.setOnMouseClicked(event->{
            slideBack(slider);
        });
        
        slider.setTranslateX(-210);
        menueback.setOnMouseClicked(event->{
                slide(slider);
            
        });
    }    
    
    public void render(String pathURL)
    {
         try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(pathURL));
                Parent contentView = loader.load();
                Object loaderCont = loader.getController();
                
                    // pass the data to listRecTestController
                    if(loaderCont instanceof ListRecTestController){
                    ListRecTestController recCont = loader.getController();
                    recCont.setFrontController(this);
                    //recCont.sb();
                    }
                    if (loaderCont instanceof ListPayController)
                    {
                        ListPayController payCont = loader.getController();
                        payCont.setFrontController(this);
                    }
                    //recCont.setDashboard1Controller(this);
                    //recCont.setFxm(fxm);
                
                anchor.getChildren().clear();
                anchor.getChildren().add(contentView);
                
//                slider.setTranslateX(-210);
//
//                slide(slider);
                //menueback.fireEvent((Event)menueback.getOnMouseClicked());
                //Event.fireEvent(menueback, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.NONE, 0, true, true, true, true, true, true, true, true, true, true, null));
                //AnchorPane.setTopAnchor(label, tabWidth);
                AnchorPane.setTopAnchor(contentView,0.0);
                AnchorPane.setBottomAnchor(contentView,0.0);
                AnchorPane.setRightAnchor(contentView, 0.0);
                AnchorPane.setLeftAnchor(contentView, 0.0);
                //containerPane.setPrefSize(AnchorPane.USE_COMPUTED_SIZE, AnchorPane.USE_COMPUTED_SIZE);
            } catch (IOException e) {
                e.printStackTrace();
            }
    
    }


    @FXML
    private void listRecTest(javafx.event.ActionEvent event) {
        render("listRecTest.fxml");
    }
    
    public void test()
    {
     Event.fireEvent(menu, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.NONE, 0, true, true, true, true, true, true, true, true, true, true, null));

    }
    public void slide(AnchorPane slider)
    {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(slider);
            slide.setToX(-210);
            slide.play();
            
            slider.setTranslateX(0);
            
            slide.setOnFinished((e)->{
                menu.setVisible(true);
                menueback.setVisible(false);
                });
    }
    
    public void slideBack(AnchorPane slider)
    {
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
    }

    @FXML
    private void listPayTest(ActionEvent event) {
        render("listPay.fxml");
    }

}


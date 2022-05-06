/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
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
public class FrontGhController implements Initializable {

    @FXML
    private Label menueback;
    @FXML
    private Label menu;
    @FXML
    private AnchorPane slider;
    @FXML
    private Label menu1;
    @FXML
    private GridPane grid;
    private List<Evenement> evenements;
    @FXML
    private JFXButton rec;
    @FXML
    private JFXButton pay;
    @FXML
    private AnchorPane anchor;

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
                //System.out.println(grid);
                //System.out.println(pane);
                grid.add(pane, column++, row);
                grid.setMargin(pane, new Insets(20));
            }
                
        }catch(IOException e){e.printStackTrace();};
        
    }    

    private void GoToWeather(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Weather.fxml"));
        Parent root = loader.load(); 
        grid.getScene().setRoot(root);
    }

    @FXML
    private void destination(javafx.event.ActionEvent event) {
        //TODO
    }

    @FXML
    private void event(javafx.event.ActionEvent event) {
        //NO NEED
    }

    public void render(String pathURL)
    {
         try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(pathURL));
                Parent contentView = loader.load();
                Object loaderCont = loader.getController();
                
                    // pass the data to listRecTestController
//                    if(loaderCont instanceof ListRecTestController){
//                    ListRecTestController recCont = loader.getController();
//                    recCont.setFrontController(this);
//                    //recCont.sb();
//                    }
//                    if (loaderCont instanceof ListPayController)
//                    {
//                        ListPayController payCont = loader.getController();
//                        payCont.setFrontController(this);
//                    }
//                    //recCont.setDashboard1Controller(this);
//                    //recCont.setFxm(fxm);
                
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

    @FXML
    private void listPayTest(javafx.event.ActionEvent event) {
                render("listPay.fxml");

    }
    
}
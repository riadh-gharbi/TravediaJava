/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.animation.TranslateTransition;  
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
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
    private AnchorPane slider;
    @FXML
    private Label menu1;
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXButton pay;
    @FXML
    private JFXButton rec;
    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane evScroll;
    @FXML
    private BorderPane borderPane;
    
    public AnchorPane GetSlider()
    {
        return slider;
    }
    public AnchorPane getAnchorPane()
    {
        return this.anchor;
    }
//    @FXML
//    private Button supprimer;
//    @FXML
//    private Button modifier;

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
        Iterator<Node> itr = anchor.getChildren().iterator();
        while(itr.hasNext())
        {
            Node p = itr.next();
            if (p instanceof Pane)
            {
                itr.remove();
            }
        }
//         for(Node p:anchor.getChildren())
//                {
//                    if(p instanceof Pane)
//                    {
//                        anchor.getChildren().remove(p);
//                    }
//                }
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
                    anchor.getChildren().stream().map((p) -> {
                        p.setVisible(false);
                         return p;
                         }).forEachOrdered((p) -> {
                        p.setDisable(true);
                    });
               
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

    @FXML
    private void destination(ActionEvent event) {
        
    }

    @FXML
    private void event(ActionEvent event) {
        renderEvent();
           

    }
    
    @FXML
    public void renderProfile()
    {
        evScroll.setDisable(true);
                 evScroll.setVisible(false);
        Iterator<Node> itr = anchor.getChildren().iterator();
        while(itr.hasNext())
        {
            Node p = itr.next();
            if(!(p instanceof ScrollPane))
            {
                itr.remove();
            }
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("profilePane.fxml"));
            Node profilePane = loader.load();
            
            
            anchor.getChildren().add(profilePane);
            profilePane.setLayoutX(745);
            
        } catch (IOException ex) {
            System.err.println("profile pane error :"+ex.getMessage());
        }
    }
    
        public void renderEvent()
    {
                 EvenementService evser = new EvenementService();
                 evScroll.setDisable(false);
                 evScroll.setVisible(true);
                 
        List<Evenement> evenements = evser.recuperer();
        int column = 0;
        int row = 1;
        Iterator<Node> itr = anchor.getChildren().iterator();
        while(itr.hasNext())
        {
            Node p = itr.next();
            if(p instanceof ScrollPane)
            {
             p.setDisable(false);
                p.setVisible(true);
            }else
            {
                itr.remove();
            }
        }
//        for(Node p:anchor.getChildren())
//        {
//            if(p instanceof ScrollPane)
//            {
//                p.setDisable(false);
//                p.setVisible(true);
//            }else
//            {
//                anchor.getChildren().remove(p);
//            }
//        }
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

}


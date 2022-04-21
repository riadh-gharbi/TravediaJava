/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Razer
 */
public class DASHBOARDController implements Initializable {

    @FXML
    private JFXTabPane tabContainer;
    @FXML
    private Tab PlaTab;
    @FXML
    private AnchorPane PlaCont;
    @FXML
    private Tab HotTab;
    @FXML
    private AnchorPane HotConts;

   private double tabWidth = 90.0;
    public static int lastSelectedTabIndex = 0;
    

        private void configureTabPane() {
    
    tabContainer.setTabMinWidth(tabWidth);
    tabContainer.setTabMaxWidth(tabWidth);
    tabContainer.setTabMinHeight(tabWidth);
    tabContainer.setTabMaxHeight(tabWidth);
    tabContainer.setRotateGraphic(true);

}

 private void configureView() {
        tabContainer.setRotateGraphic(true);

        EventHandler<Event> replaceBackgroundColorHandler = event -> {
            lastSelectedTabIndex = tabContainer.getSelectionModel().getSelectedIndex();

            Tab currentTab = (Tab) event.getTarget();
            if (currentTab.isSelected()) {
                currentTab.setStyle("-fx-background-color: -fx-focus-color;");
            } else {
                currentTab.setStyle("-fx-background-color: -fx-accent;");
            }
        };

        EventHandler<Event> logoutHandler = event -> {
            Tab currentTab = (Tab) event.getTarget();
            if (currentTab.isSelected()) {
                tabContainer.getSelectionModel().select(lastSelectedTabIndex);

                
                System.out.println("Logging out!");
            }
        };

       
        configureTab(PlaTab, "Planning", "gui/images/icons8-exclamation-64.png", PlaCont, getClass().getResource("PlaList.fxml"), replaceBackgroundColorHandler);
        configureTab(HotTab, "Hotel", "gui/images/icons8-payment-64.png", HotConts, getClass().getResource("HotelList.fxml"), replaceBackgroundColorHandler);

     
    }

    private void configureTab(Tab tab, String title, String iconPath, AnchorPane containerPane, URL resourceURL, EventHandler<Event> onSelectionChangedEvent) {
        double imageWidth = 20.0;

        ImageView imageView = new ImageView(new Image(iconPath));
        imageView.setFitHeight(imageWidth);
        imageView.setFitWidth(imageWidth);
        

        Label label = new Label(title);
        label.setMaxWidth(tabWidth - 10 );
        label.setPadding(new Insets(0, 0, 0, 4));
        label.setStyle("-fx-text-fill: white; -fx-font-size: 8pt; -fx-font-weight: normal;");
        label.setTextAlignment(TextAlignment.CENTER);

        BorderPane tabPane = new BorderPane();
        tabPane.setRotate(90.0);
                
        tabPane.setLeft(imageView);
        tabPane.setCenter(label);

        tab.setText("");
        tab.setGraphic(tabPane);

        tab.setOnSelectionChanged(onSelectionChangedEvent);

        if (containerPane != null && resourceURL != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(resourceURL);
                Parent contentView = loader.load();
                Object loaderCont = loader.getController();
                if (loaderCont instanceof HotelListController)
                {
                    
                    HotelListController hotCont = loader.getController();
                    hotCont.setDASHBOARDController(this);
                }else if (loaderCont instanceof ListPlaController)
                {
                    ListPlaController PlaCont = loader.getController();
                    PlaCont.setDASHBOARDController(this);
                
                }
                
                containerPane.getChildren().add(contentView);
                
                AnchorPane.setTopAnchor(contentView,0.0);
                AnchorPane.setBottomAnchor(contentView,0.0);
                AnchorPane.setRightAnchor(contentView, 0.0);
                AnchorPane.setLeftAnchor(contentView, 0.0);
                //containerPane.setPrefSize(AnchorPane.USE_COMPUTED_SIZE, AnchorPane.USE_COMPUTED_SIZE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configureView();
        
    }    
     public AnchorPane getPlaCont() {
        return PlaCont;
    }

    public AnchorPane getHotConts() {
        return HotConts;
    }
}
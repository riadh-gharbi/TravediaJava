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
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class Dashboard1Controller implements Initializable {

    @FXML
    private BorderPane Center;
    @FXML
    private Pane TitleButtons;

     @FXML
    private AnchorPane userCont;
      @FXML
    private AnchorPane recCont;
       @FXML
    private AnchorPane payConts;

   



    @FXML
    private Tab RecTab;
    @FXML
    private Tab PayTab;
    @FXML
    private Tab UserTab;
    @FXML
    private JFXTabPane tabContainer;
    private double tabWidth = 90.0;
    public static int lastSelectedTabIndex = 0;
    private FXMain fxm; 

    public FXMain getFxm() {
        return fxm;
    }

    public void setFxm(FXMain fxm) {
        this.fxm = fxm;
    }
    
    private void configureTabPane() {
    /// 3.
    tabContainer.setTabMinWidth(tabWidth);
    tabContainer.setTabMaxWidth(tabWidth);
    tabContainer.setTabMinHeight(tabWidth);
    tabContainer.setTabMaxHeight(tabWidth);
    tabContainer.setRotateGraphic(true);
    
    /// 4.
//    configureTab(UserTab, "User\nProfile", "icons8-user-48.png");
//    configureTab(RecTab, "Reclamations", "icons8-exclamation-64.png");
//    configureTab(PayTab, "Paiements", "icons8-payment-64.png");
}

 public void configureView() {
//         tabContainer.setTabMinWidth(50);
//    tabContainer.setTabMaxWidth(50);
//    tabContainer.setTabMinHeight(tabWidth);
//    tabContainer.setTabMaxHeight(tabWidth);
        tabContainer.setRotateGraphic(true);

        EventHandler<Event> replaceBackgroundColorHandler = event -> {
            lastSelectedTabIndex = tabContainer.getSelectionModel().getSelectedIndex();

            Tab currentTab = (Tab) event.getTarget();
            if (currentTab.isSelected()) {
                currentTab.setStyle("-fx-background-color: -fx-focus-color;");
            } else {
                currentTab.setStyle("-fx-background-color: -fx-accent;");
            }
            configureView();
        };

        EventHandler<Event> logoutHandler = event -> {
            Tab currentTab = (Tab) event.getTarget();
            if (currentTab.isSelected()) {
                tabContainer.getSelectionModel().select(lastSelectedTabIndex);

                // TODO: logout action
                // good place to show Dialog window with Yes / No question
                System.out.println("Logging out!");
            }
        };

        configureTab(UserTab, "User\nProfile", "gui/images/icons8-user-48.png", userCont, getClass().getResource("AddReclamation.fxml"), replaceBackgroundColorHandler);
        configureTab(RecTab, "Reclamation", "gui/images/icons8-exclamation-64.png", recCont, getClass().getResource("listRecTest.fxml"), replaceBackgroundColorHandler);
        configureTab(PayTab, "Paiements", "gui/images/icons8-payment-64.png", payConts, getClass().getResource("listPay.fxml"), replaceBackgroundColorHandler);

       // UserTab.setStyle("-fx-background-color: -fx-focus-color;");
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
        //tabPane.setMaxWidth(tabWidth);
        
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
                if (loaderCont instanceof ListRecTestController)
                {
                    // pass the data to listRecTestController
                    ListRecTestController recCont = loader.getController();
                    recCont.setDashboard1Controller(this);
                    //recCont.setFxm(fxm);
                }else if (loaderCont instanceof ListPayController)
                {
                    ListPayController payCont = loader.getController();
                   // payCont.setDashboard1Controller(this);
                
                }
                containerPane.getChildren().clear();
                containerPane.getChildren().add(contentView);
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
    }
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fxm = FXMain.instance;
        configureView();
        System.out.println("DASHBOARD FXM IS HERE " + fxm);
        
    }    
     public AnchorPane getRecCont() {
        return recCont;
    }

    public AnchorPane getPayConts() {
        return payConts;
    }
}

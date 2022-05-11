/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import entities.Destination;
import entities.Evenement;
import entities.Region;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.animation.TranslateTransition;  
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import services.DestinationService;
import services.EvenementService;
import services.RegionService;
import services.UtilisateurService;
import util.Session;

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
    @FXML
    private JFXButton dashboard;
    
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
        
        //Disable or enable dashboard button
        UtilisateurService us = new UtilisateurService();
        FXMain.instance.setFrontController(this);
        if("admin".equals(us.checkRole(Session.getUser())))
        {
            dashboard.setDisable(false);
            dashboard.setVisible(true);
        }
        //Set up menu slider events
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

//    private void destination(ActionEvent event) {
//        renderDest();
//    }

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
        grid.getChildren().clear();
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
    @FXML
        public void renderRegion()
        {
              grid.getChildren().clear();
             evScroll.setDisable(false);
                 evScroll.setVisible(true);
        int column=0;
        int row=0;
         RegionService ps = new RegionService();
            List<Region> regions =ps.recuperer();
            ObservableList list = FXCollections.observableArrayList(regions);
          List<Node> nodes = new ArrayList<>();
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
//        
     //   for(Region c : regions){
            try{
              for(int i=0;i<regions.size();i++){
                FXMLLoader loader = new FXMLLoader();
                
                loader.setLocation(getClass().getResource("ItemRegionFront.fxml"));
              //  Parent root =loader.load();
              //  nodes.add( root);
                AnchorPane ap =loader.load();
                //vbox.getChildren().add(root);
                ItemRegionFrontController itemController = new ItemRegionFrontController();
                itemController = loader.getController();
                itemController.setData(regions.get(i));
                itemController.setParentController(this);
                if(column==3){
                    column=0;
                    row++;
                }
                grid.add(ap,column++,row);
               // grid.getColumnConstraints().add(new ColumnConstraints(450,250,250)); // column 0 is 100 wide
                GridPane.setMargin(ap,new Insets(10));
              }
            }catch (IOException e){
                    e.printStackTrace();
                    e.getMessage();
            }
            
        }
        public void renderDest(Region r)
        {
            grid.getChildren().clear();
             evScroll.setDisable(false);
                 evScroll.setVisible(true);
                 int column=0;
        int row=0;
        int id=11;
         DestinationService ps = new DestinationService();
            List<Destination> dest =ps.getDestinationParRegion(r.getId());
            //List<Destination> dest =ps.recuperer();
            ObservableList list = FXCollections.observableArrayList(dest);
          List<Node> nodes = new ArrayList<>();
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
     //   for(Region c : regions){
            try{
              for(int i=0;i<dest.size();i++){
                FXMLLoader loader = new FXMLLoader();
                
                loader.setLocation(getClass().getResource("ItemDestinationFront.fxml"));
              //  Parent root =loader.load();
              //  nodes.add( root);
                AnchorPane ap =loader.load();
                //vbox.getChildren().add(root);
                ItemDestinationFrontController itemController;
                itemController = loader.getController();
                itemController.setParentController(this);
                itemController.setData(dest.get(i));
                if(column==3){
                    column=0;
                    row++;
                }
                grid.add(ap,column++,row);
               // griddest.getColumnConstraints().add(new ColumnConstraints(100));
               // grid.getColumnConstraints().add(new ColumnConstraints(450,250,250)); // column 0 is 100 wide
                GridPane.setMargin(ap,new Insets(10));
              }
            }catch (IOException e){
                    e.printStackTrace();
                    e.getMessage();
            }
        
        }
        
        public void detailsDest(Destination d)
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsDestinationSimple.fxml"));
            Parent root =loader.load();
            DetailsDestinationController dc = loader.getController();
            dc.setDestination(d);
             anchor.getChildren().stream().map((p) -> {
                        p.setVisible(false);
                         return p;
                         }).forEachOrdered((p) -> {
                        p.setDisable(true);
                    });
               
                anchor.getChildren().add(root);
                
//                slider.setTranslateX(-210);
//
//                slide(slider);
                //menueback.fireEvent((Event)menueback.getOnMouseClicked());
                //Event.fireEvent(menueback, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.NONE, 0, true, true, true, true, true, true, true, true, true, true, null));
                //AnchorPane.setTopAnchor(label, tabWidth);
                AnchorPane.setTopAnchor(root,0.0);
                AnchorPane.setBottomAnchor(root,0.0);
                AnchorPane.setRightAnchor(root, 0.0);
                AnchorPane.setLeftAnchor(root, 0.0);
            
        } catch (IOException ex) {
            System.err.println(ex.getCause() + " " + ex.getMessage());
        }
        
        }

    @FXML
    private void goToDashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();
            
            
            FXMain.instance.setBackController(loader.getController());
            anchor.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void postes(ActionEvent event) {
        render("PostList.fxml");
    }

    @FXML
    private void plannings(ActionEvent event) {
            render("ListePlanningFront.fxml");
    }

    @FXML
    private void signOut(ActionEvent event) {
        try {
            UtilisateurService us = new UtilisateurService();
            us.logout();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("user log.fxml"));
            Parent root = loader.load();
            anchor.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println("");
        }
        
    }

}


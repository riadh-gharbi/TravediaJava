/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Hotel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.HotelService;

/**
 * FXML Controller class
 *
 * @author Razer
 */
public class HotelListController implements Initializable {

    @FXML
    private HBox FieldNames;
    @FXML
    private VBox vList;
    @FXML
    private BorderPane rootParent;
    @FXML
    private BorderPane table;
    @FXML
    private VBox ObjectVBox;

     private DASHBOARDController  DASHBOARDController;
    private List<Hotel> Hotlist=  new ArrayList<>();
    
    public void setDASHBOARDController(DASHBOARDController DASHBOARDController) {
        this. DASHBOARDController = DASHBOARDController ;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         HotelService rs = new HotelService();
        Hotlist = rs.recuperer();
        int row = 0;
        
        for(Hotel r: Hotlist)
        {
           

            try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("HotelItem.fxml"));
            Parent root = loader.load();
            vList.getChildren().add(root);
            HotelItemController hotItemController;
            hotItemController = loader.getController();
            hotItemController.setHotels(rs.recuperer(r.getId()));
            } catch (IOException ex)
            {
                System.err.println("List Hot Error : " + ex.getMessage() + " " + ex.getCause());
            }
            
        }
    }
       

    @FXML
    private void goAjoutHot(ActionEvent event) {
try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Hotel.fxml"));
            Parent root = loader.load();
            if (DASHBOARDController != null)
            {
                DASHBOARDController.getHotConts().getChildren().clear();
                DASHBOARDController.getPlaCont().getChildren().add(root);
            }
           
        } catch (IOException ex) {
            System.err.println("Error Ajout Hot button "+ ex.getMessage() + " "+ ex.getCause());
        }
    }
    }

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Hotel;
import entities.Planning;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import services.HotelService;
import services.PlanningService;

/**
 * FXML Controller class
 *
 * @author Razer
 */
public class ListPlanningController implements Initializable {

    @FXML
    private BorderPane table;
    @FXML
    private VBox ObjectVBox;
    @FXML
    private VBox vList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    refreshList();
    } 
    public void refreshList()
    {
        PlanningService plaser = new PlanningService();
        HotelService hotserv = new HotelService();
        List<Hotel> hottel = hotserv.recupererListe();
        System.out.println(hottel);
        List<Planning> plan = plaser.recuperer();
        ObservableList list = FXCollections.observableArrayList(plan);
        ObservableList lis1t = FXCollections.observableArrayList(hottel);
        vList.getChildren().clear();
        List<Node> nodes = new ArrayList<>();
        
        for(Planning p : plan){
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("PlanningItem.fxml"));
                Parent root =loader.load();
                nodes.add(root);
                vList.getChildren().add(root);
                PlaItemController itemPlaController = new PlaItemController();
                itemPlaController = loader.getController();
                itemPlaController.setplanning(p);
                itemPlaController.setParentController(this);
            }catch (IOException e){
                    e.printStackTrace();
            }
        }
    }

    
           
    @FXML
    private void ajouterplanning(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Planning.fxml"));
                 Parent root = loader.load(); 
        vList.getScene().setRoot(root);
    }

    }
    
    

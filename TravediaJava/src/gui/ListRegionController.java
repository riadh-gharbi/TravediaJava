/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Region;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import services.RegionService;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class ListRegionController implements Initializable {

    @FXML
    private VBox vitem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // RegionService catser = new RegionService();
    RegionService catser = new RegionService();
        List<Region> categorie = catser.recuperer();
        ObservableList list = FXCollections.observableArrayList(categorie);
        
        List<Node> nodes = new ArrayList<>();
        
        for(Region c : categorie){
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ItemRegion.fxml"));
                Parent root =loader.load();
                nodes.add( root);
                vitem.getChildren().add(root);
                ItemRegionController itemController = new ItemRegionController();
                itemController = loader.getController();
                itemController.setRegion(c);
            }catch (IOException e){
                    e.printStackTrace();
            }
        }
    }   
    
}

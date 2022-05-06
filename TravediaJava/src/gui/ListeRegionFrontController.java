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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import services.RegionService;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class ListeRegionFrontController implements Initializable {

   
    private VBox vbox;
    @FXML
    private Label menueback;
    @FXML
    private Label menu;
    @FXML
    private Label menu1;
    @FXML
    private GridPane grid;
    @FXML
    private GridPane grid2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO RegionService ps = new RegionService();
        
        int column=0;
        int row=0;
         RegionService ps = new RegionService();
            List<Region> regions =ps.recuperer();
            ObservableList list = FXCollections.observableArrayList(regions);
          List<Node> nodes = new ArrayList<>();
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
    }  
//}
    


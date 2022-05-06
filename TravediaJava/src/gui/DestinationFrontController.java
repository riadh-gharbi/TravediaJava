/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Destination;
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
import services.DestinationService;
import services.RegionService;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class DestinationFrontController implements Initializable {

    @FXML
    private Label menueback;
    @FXML
    private Label menu;
    @FXML
    private Label menu1;
    @FXML
    private GridPane griddest;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         int column=0;
        int row=0;
        int id=11;
         DestinationService ps = new DestinationService();
          //  List<Destination> dest =ps.getDestinationParRegion(id);
            List<Destination> dest =ps.recuperer();
            ObservableList list = FXCollections.observableArrayList(dest);
          List<Node> nodes = new ArrayList<>();
//        
     //   for(Region c : regions){
            try{
              for(int i=0;i<dest.size();i++){
                FXMLLoader loader = new FXMLLoader();
                
                loader.setLocation(getClass().getResource("ItemDestinationFront.fxml"));
              //  Parent root =loader.load();
              //  nodes.add( root);
                AnchorPane ap =loader.load();
                //vbox.getChildren().add(root);
                ItemDestinationFrontController itemController = new ItemDestinationFrontController();
                itemController = loader.getController();
                itemController.setData(dest.get(i));
                if(column==3){
                    column=0;
                    row++;
                }
                griddest.add(ap,column++,row);
               // griddest.getColumnConstraints().add(new ColumnConstraints(100));
               // grid.getColumnConstraints().add(new ColumnConstraints(450,250,250)); // column 0 is 100 wide
                GridPane.setMargin(ap,new Insets(10));
              }
            }catch (IOException e){
                    e.printStackTrace();
                    e.getMessage();
            }
        }
    }    
    


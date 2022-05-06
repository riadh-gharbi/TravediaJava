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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import services.DestinationService;
import services.RegionService;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class AfficherDestinationController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Button addbtn;
    @FXML
    private Button btndest;
    @FXML
    private Button listregion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       DestinationService ps = new DestinationService();
            List<Destination> destinations =ps.recuperer();
            ObservableList list = FXCollections.observableArrayList(destinations);
          List<Node> nodes = new ArrayList<>();
//        
        for(Destination c : destinations){
            try{
              
                FXMLLoader loader = new FXMLLoader();
                
                loader.setLocation(getClass().getResource("ItemDestination.fxml"));
                Parent root =loader.load();
                nodes.add( root);
                vbox.getChildren().add(root);
                ItemDestinationController itemController = new ItemDestinationController();
                itemController = loader.getController();
                itemController.setDestination(c);
            }catch (IOException e){
                    e.printStackTrace();
                    e.getMessage();
            } }
    }
 @FXML
    private void BtnAjouterDest(ActionEvent event) throws IOException {
        
        
          Parent root = FXMLLoader.load(getClass().getResource("AjouterDestinationn.fxml"));
               addbtn.getScene().setRoot(root);
    }    

    @FXML
    private void opendest(ActionEvent event) throws IOException {
        
          Parent root = FXMLLoader.load(getClass().getResource("AfficherDestination.fxml"));
               btndest.getScene().setRoot(root);
    }

    @FXML
    private void listregion(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("AfficherRegion.fxml"));
               listregion.getScene().setRoot(root);
    }
    
}

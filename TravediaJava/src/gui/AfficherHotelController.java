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
import services.HotelService;

/**
 * FXML Controller class
 *
 * @author Razer
 */
public class AfficherHotelController implements Initializable {

    @FXML
    private VBox vItem = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshList();
    } 
    public void refreshList()
    {
        HotelService hoser = new HotelService();
        List<Hotel> hotel = hoser.recuperer();
        ObservableList list = FXCollections.observableArrayList(hotel);
        vItem.getChildren().clear();
        List<Node> nodes = new ArrayList<>();
        
        for(Hotel h : hotel){
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ItemHotel.fxml"));
                Parent root =loader.load();
                nodes.add(root);
                vItem.getChildren().add(root);
                ItemHotelController itemHotelController = new ItemHotelController();
                itemHotelController = loader.getController();
                itemHotelController.setHotel(h);
                itemHotelController.setParentController(this);
            }catch (IOException e){
                    e.printStackTrace();
            }
        }
    }

    
    @FXML
    private void AjouterHotel(ActionEvent event) throws IOException {
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterHotel.fxml"));
//        Parent root = loader.load(); 
//        vItem.getScene().setRoot(root);
          FXMain.instance.getBackController().render("AjouterHotelSimple.fxml");
    }

    @FXML
    private void golisteplanning(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Planning.fxml"));
        Parent root = loader.load(); 
        vItem.getScene().setRoot(root);
    }
    }
    


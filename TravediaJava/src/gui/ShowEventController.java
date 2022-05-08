/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import entities.Evenement;
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
import javafx.scene.layout.VBox;
import services.CategorieService;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ShowEventController implements Initializable {

    @FXML
    private VBox vItem= null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshList();
    }   
    public void refreshList()
    {
        EvenementService evser = new EvenementService();
        List<Evenement> evenement = evser.recuperer();
        ObservableList list = FXCollections.observableArrayList(evenement);
        vItem.getChildren().clear();
        List<Node> nodes = new ArrayList<>();
        
        for(Evenement ev : evenement){
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ItemEv.fxml"));
                Parent root =loader.load();
                nodes.add( root);
                vItem.getChildren().add(root);
                ItemEvController itemEvController = new ItemEvController();
                itemEvController = loader.getController();
                itemEvController.setEvenement(ev);
                itemEvController.setParentController(this);
            }catch (IOException e){
                    e.printStackTrace();
            }
        }
    }

    @FXML
    private void ShowEventList(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowEvent.fxml"));
//        Parent root = loader.load(); 
//        vItem.getScene().setRoot(root);
          FXMain.instance.getBackController().render("showEventSimple.fxml");
    }

    @FXML
    private void ShowCategList(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowCategorie.fxml"));
//        Parent root = loader.load(); 
//        vItem.getScene().setRoot(root);
          FXMain.instance.getBackController().render("ShowCategorieSimple.fxml");
    }

    @FXML
    private void AddEvent(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEventSimple.fxml"));
//        Parent root = loader.load(); 
//        vItem.getScene().setRoot(root);
          FXMain.instance.getBackController().render("AddEventSimple.fxml");
    }

    @FXML
    private void GoToStat(ActionEvent event) throws IOException {
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("StatistiqueSimple.fxml"));
//        Parent root = loader.load(); 
//        vItem.getScene().setRoot(root);
            FXMain.instance.getBackController().render("StatistiqueSimple.fxml");
    }
     
    
}

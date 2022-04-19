/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.util.Callback;
import services.CategorieService;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;



/**
 * FXML Controller class
 *
 * @author user
 */
public class ShowCategorieController implements Initializable {

    @FXML
    private VBox vItem = null;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CategorieService catser = new CategorieService();
        List<Categorie> categorie = catser.recuperer();
        ObservableList list = FXCollections.observableArrayList(categorie);
        
        List<Node> nodes = new ArrayList<>();
        
        for(Categorie c : categorie){
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Item.fxml"));
                Parent root =loader.load();
                nodes.add( root);
                vItem.getChildren().add(root);
                ItemController itemController = new ItemController();
                itemController = loader.getController();
                itemController.setCategorie(c);
            }catch (IOException e){
                    e.printStackTrace();
            }
        }
    }   
    
   
    
}

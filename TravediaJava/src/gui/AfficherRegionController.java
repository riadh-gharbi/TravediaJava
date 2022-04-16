/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Region;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.RegionService;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class AfficherRegionController implements Initializable {

    @FXML
    private TableView<Region> tablev;
    @FXML
    private TableColumn<Region, String> Image;
    @FXML
    private TableColumn<Region, String> Region;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //  try {
            RegionService ps = new RegionService();
            List<Region> regions =ps.recuperer();
            ObservableList list = FXCollections.observableArrayList(regions);
           // list.remove();
           tablev.setItems(list);
           Image.setCellValueFactory(new PropertyValueFactory<>("image"));
           Region.setCellValueFactory(new PropertyValueFactory<>("nom"));
           
     //   } catch (SQLException ex) {
        //    System.out.println(ex.getMessage());        }
    }    
    }    
    


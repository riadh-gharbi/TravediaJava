/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
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
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class RecListBackController implements Initializable {

    @FXML
    private BorderPane rootParent;
    @FXML
    private GridPane listGrid;
    @FXML
    private BorderPane Table;
    @FXML
    private HBox FieldNames;
    @FXML
    private VBox ObjectVBox;
    @FXML
    private VBox vList;
    
    private DashboardController dashboardController;
    
    private ObservableList<Reclamation> obsRec = FXCollections.observableArrayList();
    private List<Reclamation> listRec = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dashboardController = FXMain.instance.getBackController();
        FXMain.instance.Recuperer();
        listRec = FXMain.instance.getReclamationData();
        ReclamationService rs = new ReclamationService();
        for(Reclamation r : listRec)
        {
            //Add entries in loop
            //if(r.getUser_id() == currentUserId)
            try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("recItemBack.fxml"));
            Parent root = loader.load();
            vList.getChildren().add(root);
            RecItemBackController recItemBackController;
            recItemBackController = loader.getController();
            //recItemController.setDashboard1Controller(dashboardController);
            //recItemController.setFxm(fxm);
            recItemBackController.setReclamation(r, rs.recupererReponse(r.getId()));
            } catch (IOException ex)
            {
                System.err.println("List Rec Error : " + ex.getMessage() + " " + ex.getCause());
            }
        
        }
        
    }    

    @FXML
    private void goAjoutRec(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AddReclamationBack.fxml"));
            Parent root = loader.load();
            if (dashboardController != null)
            {
                dashboardController.getAnchor().getChildren().clear();
                dashboardController.getAnchor().getChildren().add(root);
                
            }
            AddReclamationBackController addReclamationBackController = loader.getController();
            addReclamationBackController.setDashboard1Controller(dashboardController);
            addReclamationBackController.setPrevious("recListBack.fxml");
           
        } catch (IOException ex) {
            System.err.println("Error Ajout Rec button "+ ex.getMessage() + " "+ ex.getCause());
        }
        
    }
    
}

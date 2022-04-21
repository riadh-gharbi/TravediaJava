/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class ListRecTestController implements Initializable {

    @FXML
    private BorderPane Table;
    @FXML
    private HBox FieldNames;
    @FXML
    private VBox ObjectVBox;
    @FXML
    private GridPane listGrid;
    
    private Dashboard1Controller dashboard1Controller;
    private List<Reclamation> reclist=  new ArrayList<>();
    @FXML
    private VBox vList;
    @FXML
    private BorderPane rootParent;
    
    private FXMain fxm;

    public FXMain getFxm() {
        return fxm;
    }

    public void setFxm(FXMain fxm) {
        this.fxm = fxm;
    }

    public void setDashboard1Controller(Dashboard1Controller dashboard1Controller) {
        this.dashboard1Controller = dashboard1Controller;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationService rs = new ReclamationService();
        reclist = rs.recuperer();
        int row = 0;
        
        for(Reclamation r: reclist)
        {
            
//            Label sujet= new Label(r.getSujet());
//            Label contenu = new Label(r.getContenu());
//            Label etat=new Label(r.getEtat_reclamation());
//            Label user = new Label("Utilisateur "+r.getUser_id().toString());
//            Label response = new Label(rs.recupererReponse(r.getId()).getContenu());
//            RowConstraints rc = new RowConstraints();
//            rc.setMinHeight(50.0);
//            listGrid.getRowConstraints().add(rc);
//
//            listGrid.add(sujet, 0, row);
//            listGrid.add(contenu, 1, row);
//            listGrid.add(etat, 2, row);
//            listGrid.add(user, 3, row);
//            listGrid.add(response, 4, row);
//            JFXButton buttonModif = new JFXButton("Modifier");
//            JFXButton buttonSuppr = new JFXButton("Supprimer");
//            listGrid.add(buttonModif,5,row);
//            listGrid.add(buttonSuppr,6,row);
//            row++;
            try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("recItem.fxml"));
            Parent root = loader.load();
            vList.getChildren().add(root);
            RecItemController recItemController;
            recItemController = loader.getController();
            recItemController.setDashboard1Controller(dashboard1Controller);
            //recItemController.setFxm(fxm);
            recItemController.setReclamation(r, rs.recupererReponse(r.getId()));
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
            loader.setLocation(getClass().getResource("AddReclamation.fxml"));
            Parent root = loader.load();
            if (dashboard1Controller != null)
            {
                dashboard1Controller.getRecCont().getChildren().clear();
                dashboard1Controller.getRecCont().getChildren().add(root);
                
            }
            AddReclamationController addReclamationController = loader.getController();
            addReclamationController.setDashboard1Controller(dashboard1Controller);
            addReclamationController.setPrevious("listRecTest.fxml");
           
        } catch (IOException ex) {
            System.err.println("Error Ajout Rec button "+ ex.getMessage() + " "+ ex.getCause());
        }
    }
    
}

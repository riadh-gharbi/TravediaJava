/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import entities.Evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.sql.Date;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import services.CategorieService;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ItemEvController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private Label datedeb;
    @FXML
    private Label datefin;
    @FXML
    private Label category;
    @FXML
    private Label image;
    Evenement currentEvent;
    ShowEventController controller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
     public void setEvenement(Evenement ev){
        nom.setText(ev.getNom());
        description.setText(ev.getDescription());
        datedeb.setText(ev.getDatedeb().toString());
        datefin.setText(ev.getDatefin().toString());
        //category.setText(ev.getCategorie().toString());
        image.setText(ev.getImage());
        
    }

    @FXML
    private void edit(ActionEvent event) {
    }

    public void setParentController(ShowEventController contr)
    {
        controller = contr;
    }
    
    @FXML
    private void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are You Sure ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new EvenementService().supprimer(currentEvent);
        controller.refreshList();
    }}
    
}

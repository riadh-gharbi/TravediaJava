/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import entities.Planning;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import services.PlanningService;

/**
 * FXML Controller class
 *
 * @author Razer
 */
public class PlaItemController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private Label datedepart;
    @FXML
    private Label datefin;
    @FXML
    private Label prix;
    @FXML
    private Label destination;
    @FXML
    private Label typeplan;
    @FXML
    private Label evenement;
    @FXML
    private Label hotel;
    @FXML
    private JFXButton modify;
    @FXML
    private JFXButton delete;
    Planning pp;
    ListPlanningController controller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 public void setplanning(Planning p){
     pp = p;
    nom.setText(String.valueOf(p.getVoyageurId()));
    description.setText(p.getDescription());
    datedepart.setText(p.getDateDepart().toString());
    datefin.setText(p.getDateFin().toString());
    prix.setText(String.valueOf(p.getPrix()));
    destination.setText(String.valueOf(p.getPrix()));
    typeplan.setText((p.getTypePlan()));
    evenement.setText(String.valueOf(p.getEvenementId()));
    hotel.setText(String.valueOf(p.getHotelId()));
 
 
 }
 public void setParentController(ListPlanningController contr)
    {
        controller = contr;
    }
    @FXML
    private void modpla(ActionEvent event) {
     try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ListePlanning.fxml"));
            Parent root = loader.load();
            ModifierPlanningController editho = loader.getController();
            editho.setPlanning(pp);
           nom.getScene().setRoot(root);
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void deletepla(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Paiement");
        alert.setHeaderText("Attention , Vous allez supprimer un paiement!");
        alert.setContentText("Voulez vous vraiment supprimer ce paiement?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            PlanningService ps = new PlanningService();
            //System.out.println(paiement.getId());
            ps.supprimer(pp.getId());
                   } else {
            alert.close();
        }
    }
    }
    


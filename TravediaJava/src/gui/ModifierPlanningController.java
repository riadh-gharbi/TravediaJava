/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Destination;
import java.time.ZoneOffset;
import java.time.LocalDate;
import entities.Evenement;
import entities.Hotel;
import entities.Planning;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.DestinationService;
import services.EvenementService;
import services.HotelService;
import services.PlanningService;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Razer
 */
public class ModifierPlanningController implements Initializable {

    @FXML
    private TextField description;
    @FXML
    private DatePicker datefin;
    @FXML
    private DatePicker datedeb;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<String> destination;
    @FXML
    private ComboBox<String> typeplan;
    @FXML
    private ComboBox<String> evenement;
    @FXML
    private ComboBox<String> hotel;
    @FXML
    private ComboBox<String> vname;
    Planning p;

    List<Utilisateur> listuti;
    List<Destination> listdes;
    List<Evenement> listev;
    List<Hotel> listho;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listdes = new DestinationService().recuperer();
        for (Destination destinations : listdes) {
            destination.getItems().add(destinations.getNom());
        }
        listev = new EvenementService().recuperer();
        for (Evenement evenements : listev) {
            evenement.getItems().add(evenements.getNom());
        }
        listho = new HotelService().recuperer();
        for (Hotel hotels : listho) {
            hotel.getItems().add(hotels.getNom());
        }
        listuti = new UtilisateurService().recuperer();
        for (Utilisateur utils : listuti) {
            vname.getItems().add(utils.getNom());
        }
        typeplan.getItems().addAll(
        "Standard",
        "Premium"        
        
        );
        
        
    
    }    
    
    public void setPlanning(Planning pla){
        p = pla;
         int comboBoxIndex = -1;
       // vname.setText(pla.getNom());
        for (Utilisateur l : listuti) {
            comboBoxIndex++;
            if(pla.getVoyageurId()== (l).getId())break;
        }
        vname.getSelectionModel().select(comboBoxIndex);
        description.setText(pla.getDescription());
//        datedeb.setValue(pla.getDateDepart());
//        datefin.setValue(pla.getDateFin().toLocalDate());
        prix.setText(String.valueOf(pla.getPrix()));
        //image.setText(ev.getImage());
       
       
        
        for (Destination d : listdes) {
            comboBoxIndex++;
            if(pla.getDestinationId()== (d).getId())break;
        }
        destination.getSelectionModel().select(comboBoxIndex);
        
         for (Evenement E : listev) {
            comboBoxIndex++;
            if(pla.getEvenementId()== (E).getId())break;
        }
        evenement.getSelectionModel().select(comboBoxIndex);
        
         for (Hotel H : listho) {
            comboBoxIndex++;
            if(pla.getHotelId()== (H).getId())break;
        }
        hotel.getSelectionModel().select(comboBoxIndex);
        typeplan.getSelectionModel().select(comboBoxIndex);
        
    
    
    }

    @FXML
    private void modifierplanning(ActionEvent event) {
        try {
            p.setVoyageurId(Integer.parseInt(listuti.get(vname.getSelectionModel().getSelectedIndex()).getNom()));
            p.setDescription(description.getText());
            p.setDateDepart(Date.valueOf(datedeb.getValue()));
            p.setDateFin(Date.valueOf(datefin.getValue()));
            p.setPrix(Integer.parseInt(prix.getText()));
            p.setDestinationId(Integer.parseInt(listdes.get(destination.getSelectionModel().getSelectedIndex()).getNom()));
            p.setTypePlan(typeplan.getSelectionModel().getSelectedItem());
            p.setEvenementId(Integer.parseInt(listev.get(evenement.getSelectionModel().getSelectedIndex()).getNom()));
            p.setHotelId(Integer.parseInt(listho.get(hotel.getSelectionModel().getSelectedIndex()).getNom()));
            PlanningService catser = new PlanningService();
            catser.modifier(p);
            Parent root = FXMLLoader.load(getClass().getResource("ListePlanning.fxml"));
            vname.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}

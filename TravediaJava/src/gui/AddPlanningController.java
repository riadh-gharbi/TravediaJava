/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Destination;
import entities.Evenement;
import entities.Hotel;
import entities.Planning;
import entities.Utilisateur;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
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
public class AddPlanningController implements Initializable {

    @FXML
    private ChoiceBox<Utilisateur> vname; //change to string if no work
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
    private List<Utilisateur> userList = new ArrayList<>();
    private ObservableList<Utilisateur> userObsList = FXCollections.observableArrayList();
    
   // List<Utilisateur> listuti;
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
//        listuti = new UtilisateurService().recuperer();
//        for (Utilisateur utils : listuti) {
//            vname.getItems().add(utils.getNom());
//        }
        typeplan.getItems().addAll(
        "Standard",
        "Premium"        
        
        );
        UtilisateurService us = new UtilisateurService();
        userList = us.recuperer();
        
        for (Utilisateur u:userList)
        {
            userObsList.add(u);
        }
        vname.setItems(userObsList);
        vname.setConverter(new StringConverter<Utilisateur>(){
            @Override
            public String toString(Utilisateur u)
            {
                return u.getNom() + " " + u.getPrenom();
            }
            
            @Override
            public Utilisateur fromString(String string)
            {
                return vname.getItems().stream().filter(u->(u.getId()==Integer.parseInt(string))).findFirst().orElse(null);
            }
        
        });        vname.valueProperty().addListener((obs,oldval,newval)->{
            if(newval !=null){
                System.out.println("Selected User is: " + newval.getNom() + " ID: "+ newval.getId());
            }
        });
        System.out.println(userList);
   }    
    @FXML
    private void ajouterplanning(ActionEvent event) {
         Planning p = new Planning();
         Planning G = new Planning();
         Evenement e = new Evenement();
         Destination d = new Destination();
         Hotel h = new Hotel();
        G.getId();
        h.getId();
        e.getId();
        d.getId();
        p.setVoyageurId(Integer.parseInt(userObsList.get(vname.getSelectionModel().getSelectedIndex()).getNom()));
        p.setDescription(description.getText());
        p.setDateDepart(Date.valueOf(datedeb.getValue()));
        p.setDateFin(Date.valueOf(datefin.getValue()));
        p.setPrix(Integer.parseInt(prix.getText()));
        p.setDestinationId(Integer.parseInt(listdes.get(destination.getSelectionModel().getSelectedIndex()).getNom()));
        p.setTypePlan(typeplan.getSelectionModel().getSelectedItem());
        p.setEvenementId(Integer.parseInt(listev.get(evenement.getSelectionModel().getSelectedIndex()).getNom()));
        p.setHotelId(Integer.parseInt(listho.get(hotel.getSelectionModel().getSelectedIndex()).getNom()));
        PlanningService hs = new  PlanningService();
        hs.ajouter(p);
        hs.ajouter_planning_evenement(e,G);
        hs.ajouter_planning_destination(d,G);
        hs.ajouter_planning_hotel(h,G);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SUCCESS");
        alert.setContentText("Planning Added");
        alert.show();
    }
    
}

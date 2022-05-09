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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import java.util.logging.*;


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
    private ComboBox<Destination> destination;
    @FXML
    private ComboBox<String> typeplan;
    @FXML
    private ComboBox<Evenement> evenement;
    @FXML
    private ComboBox<Hotel> hotel;
    
    private List<Utilisateur> userList = new ArrayList<>();
    private ObservableList<Utilisateur> userObsList = FXCollections.observableArrayList();
    
    private List<Hotel> hotelList = new ArrayList<>();
    private ObservableList<Hotel> hotelObsList = FXCollections.observableArrayList();
    
    private List<Destination> destinationList = new ArrayList<>();
    private ObservableList<Destination> destinationObsList = FXCollections.observableArrayList();
    
    private List<Evenement> evenementList = new ArrayList<>();
    private ObservableList<Evenement> evenementObsList = FXCollections.observableArrayList();
    
    
   // List<Utilisateur> listuti;
    //List<Destination> listdes;
    //List<Evenement> listev;
    //List<Hotel> listho;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        listdes = new DestinationService().recuperer();
//        for (Destination destinations : listdes) {
//            destination.getItems().add(destinations.getNom());
//        }
//        listev = new EvenementService().recuperer();
//        for (Evenement evenements : listev) {
//            evenement.getItems().add(evenements.getNom());
//        }
//        listho = new HotelService().recuperer();
//        for (Hotel hotels : listho) {
//            hotel.getItems().add(hotels.getNom());
//        }
//        listuti = new UtilisateurService().recuperer();
//        for (Utilisateur utils : listuti) {
//            vname.getItems().add(utils.getNom());
//        }
//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
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
        
        //HOTEL 
        HotelService hs = new HotelService();
        hotelList = hs.recuperer();
        
        for (Hotel u:hotelList)
        {
            hotelObsList.add(u);
        }
        hotel.setItems(hotelObsList);
        hotel.setConverter(new StringConverter<Hotel>(){
            @Override
            public String toString(Hotel u)
            {
                return u.getNom() + " " +"";
            }
            
            @Override
            public Hotel fromString(String string)
            {
                return hotel.getItems().stream().filter(u->(u.getId()==Integer.parseInt(string))).findFirst().orElse(null);
            }
        
        });        vname.valueProperty().addListener((obs,oldval,newval)->{
            if(newval !=null){
                System.out.println("Selected User is: " + newval.getNom() + " ID: "+ newval.getId());
            }
        });
        System.out.println(hotelList);
        
        //DESTINATION 
        
        DestinationService ds = new DestinationService();
        destinationList = ds.recuperer();
        
        for (Destination u:destinationList)
        {
            destinationObsList.add(u);
        }
        destination.setItems(destinationObsList);
        destination.setConverter(new StringConverter<Destination>(){
            @Override
            public String toString(Destination u)
            {
                return u.getNom() + " " + "";
            }
            
            @Override
            public Destination fromString(String string)
            {
                return destination.getItems().stream().filter(u->(u.getId()==Integer.parseInt(string))).findFirst().orElse(null);
            }
        
        });        destination.valueProperty().addListener((obs,oldval,newval)->{
            if(newval !=null){
                System.out.println("Selected User is: " + newval.getNom() + " ID: "+ newval.getId());
            }
        });
        System.out.println(destinationList);
        
        
        //evenement
        
        EvenementService es = new EvenementService();
        evenementList = es.recuperer();
        
        for (Evenement u:evenementList)
        {
            evenementObsList.add(u);
        }
        evenement.setItems(evenementObsList);
        evenement.setConverter(new StringConverter<Evenement>(){
            @Override
            public String toString(Evenement u)
            {
                return u.getNom() + " " + "";
            }
            
            @Override
            public Evenement fromString(String string)
            {
                return evenement.getItems().stream().filter(u->(u.getId()==Integer.parseInt(string))).findFirst().orElse(null);
            }
        
        });        evenement.valueProperty().addListener((obs,oldval,newval)->{
            if(newval !=null){
                System.out.println("Selected User is: " + newval.getNom() + " ID: "+ newval.getId());
            }
        });
        System.out.println(evenementList);
        
        

        
   }    
    @FXML
    private void ajouterplanning(ActionEvent event) {
        HotelService hot = new HotelService();
        EvenementService eve = new EvenementService();
        DestinationService desti = new DestinationService();
        Planning p = new Planning();
        Evenement e = new Evenement();
        Destination d = new Destination();
        Hotel h = new Hotel();
        p.setVoyageurId(vname.getValue().getId());
        p.setDescription(description.getText());
        p.setDateDepart(Date.valueOf(datedeb.getValue()));
        p.setDateFin(Date.valueOf(datefin.getValue()));
        p.setPrix(Integer.parseInt(prix.getText()));
        p.setDestinationId(destination.getValue().getId());
        p.setTypePlan(typeplan.getSelectionModel().getSelectedItem());
        p.setEvenementId(destination.getValue().getId());
        p.setHotelId(hotel.getValue().getId());
        PlanningService hs = new  PlanningService();
        //System.out.println(datedeb.getValue());
        hs.ajouter(p, d, h, e);
        p= hs.recupererL();
        h=hot.recupererL();
        d=desti.recupererL();
        e=eve.recupererL();
        //e= eve.recuperer();
        hs.ajouter_planning_evenement(e,p);
        hs.ajouter_planning_destination(d,p);
        hs.ajouter_planning_hotel(h,p);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SUCCESS");
        alert.setContentText("Planning Added");
        alert.show();
    }
    
}

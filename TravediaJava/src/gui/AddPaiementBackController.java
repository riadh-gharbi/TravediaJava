/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDatePicker;
import entities.Paiement;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.PaiementService;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class AddPaiementBackController implements Initializable {

    @FXML
    private TextField owner;
    @FXML
    private TextField user;
    @FXML
    private TextField prix;
    @FXML
    private ChoiceBox<String> etat;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private TextField stripe;
    @FXML
    private JFXDatePicker dateC;
    @FXML
    private JFXDatePicker dateP;
    
     private String previous;
    private DashboardController dashboardController;
    @FXML
    private TextField planning;

    public DashboardController getDashboardController() {
        return dashboardController;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
     public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }
 private FXMain FXmain;
    public void setFXMain(FXMain fxm)
    {
        this.FXmain =fxm;
        
        
    }
    
    private void back()
    {
    try {
            FXMLLoader loader= new FXMLLoader();
            
            loader.setLocation(getClass().getResource(previous));
            Parent root = loader.load();
            //dashboardController.getRecCont().getChildren().clear();
            dashboardController.getAnchor().getChildren().clear();
            dashboardController.getAnchor().getChildren().add(root);
            AnchorPane.setTopAnchor(root,0.0);
                AnchorPane.setBottomAnchor(root,0.0);
                AnchorPane.setRightAnchor(root, 0.0);
                AnchorPane.setLeftAnchor(root, 0.0);
        } catch (IOException ex) {
            System.err.println("Return to list error "+ ex.getMessage() + " " + ex.getCause());
        }
    
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize choice boxes
       etat.getItems().add("En Cours");
       etat.getItems().add("Effectué");
       etat.getItems().add("Annulé");
       
       type.getItems().add("Cash");
       type.getItems().add("En ligne");
       
       
                    

        
        
          
    }
    @FXML
    private void goBack(ActionEvent event) {
        back();
    }

    @FXML
    private void addPaiement(ActionEvent event) {
        if(isInputValid()){
       Paiement p = new Paiement();
       p.setClientId(Integer.parseInt(user.getText()));
       p.setOwnerId(Integer.parseInt(owner.getText()));
       p.setPrix(Float.parseFloat(prix.getText()));
       long millis = System.currentTimeMillis();
       p.setDate_creation(new Date(millis));
       p.setStatut(etat.getValue());
       p.setType_paiement(type.getValue());
       p.setPlanningId(Integer.parseInt(planning.getText()));
       
            PaiementService ps = new PaiementService();
            ps.ajouter(p);
       
       FXMain.instance.UpdatePaiement();  
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.show();       
       back();
       }

       
                  
                 
    }
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (user.getText() == null || user.getText().length() == 0 || !isNumeric(user.getText()) ){
            errorMessage += "Voyageur non valid!\n"; 
        }
         if (owner.getText() == null || owner.getText().length() == 0 || !isNumeric(owner.getText()) ){
            errorMessage += "Guide non valid!\n"; 
        }
            
        if (prix.getText() == null || prix.getText().length() == 0 || !isNumeric(prix.getText()) ){
            errorMessage += "Prix non valid!\n"; 
        }
         if (planning.getText() == null || planning.getText().length() == 0 || !isNumeric(planning.getText()) ){
            errorMessage += "Planning non valid!\n"; 
        }
        if (etat.getValue() == null)
        {
            errorMessage += "Etat non valid!\n"; 
        }
          if (type.getValue() == null)
        {
            errorMessage += "Type non valid!\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            
            alert.setTitle("Champs invalides");
            alert.setHeaderText("Corrigez les champs invalide pour continuer");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
    public static boolean isNumeric(String string) {
    int intValue;
		
    System.out.println(String.format("Parsing string: \"%s\"", string));
		
    if(string == null || string.equals("")) {
        System.out.println("String cannot be parsed, it is null or empty.");
        return false;
    }
    
    try {
        intValue = Integer.parseInt(string);
        return true;
    } catch (NumberFormatException e) {
        System.out.println("Input String cannot be parsed to Integer.");
    }
    return false;
}
    
}

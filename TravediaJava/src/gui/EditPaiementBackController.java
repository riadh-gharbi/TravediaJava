/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDatePicker;
import entities.Paiement;
import static gui.AddPaiementBackController.isNumeric;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.PaiementService;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class EditPaiementBackController implements Initializable {

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
    
    private Paiement paiement;
    
    
     private Stage dialogStage;
    private boolean okClicked = false;
    @FXML
    private TextField planning;
    
    public void setPaiement(Paiement p)
    {
        paiement = p;
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
     /**
     * check controle saisie when user clicks ok
     */
    private void handleOk() {
        if (isInputValid()) {
            paiement.setClientId(Integer.parseInt(user.getText()));
            paiement.setOwnerId(Integer.parseInt(owner.getText()));
            paiement.setPrix(Float.parseFloat(prix.getText()));
            long millis = System.currentTimeMillis();
            paiement.setDate_creation(new Date(millis));
            paiement.setStatut(etat.getValue());
            paiement.setType_paiement(type.getValue());
            paiement.setPlanningId(Integer.parseInt(planning.getText()));
            PaiementService ps = new PaiementService();
            ps.modifier(paiement);
            

            okClicked = true;
            dialogStage.close();
        }
    }
    
      /**
     * Called when the user clicks cancel.
     */
    private void handleCancel() {
        dialogStage.close();
    }
    
     private boolean isInputValid() {
        String errorMessage = "";

        if (user.getText() == null || user.getText().length() == 0 || isNumeric(user.getText()) ){
            errorMessage += "Voyageur non valid!\n"; 
        }
         if (owner.getText() == null || owner.getText().length() == 0 || isNumeric(owner.getText()) ){
            errorMessage += "Guide non valid!\n"; 
        }
         
        if (prix.getText() == null || prix.getText().length() == 0 ||isNumeric(prix.getText()) ){
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
    @FXML
    private void editPaiement(ActionEvent event) {
                handleOk();

    }

    @FXML
    private void goBack(ActionEvent event) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import entities.ReclamationReponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class EditReclamationController implements Initializable {

    @FXML
    private TextField sujet;
    @FXML
    private TextField contenu;
    
    private int recId;
    
     private Stage dialogStage;
    private Reclamation reclamation;
    private boolean okClicked = false;
    
    private RecItemController recItemController;
    private RecItemBackController recItemBackController;
    
    public void setRecItemController(RecItemController recItemController){
        this.recItemController= recItemController;
    }
    public void setRecItemBackController(RecItemBackController recItemBackController) 
    {
        this.recItemBackController = recItemBackController;
    }
    
    Dashboard1Controller dashboard1Controller;
    @FXML
    private ChoiceBox<String> etat;

    public Dashboard1Controller getDashboard1Controller() {
        return dashboard1Controller;
    }

    public void setDashboard1Controller(Dashboard1Controller dashboard1Controller) {
        this.dashboard1Controller = dashboard1Controller;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        etat.getItems().add("En Cours");
        etat.getItems().add("Résolue");
    }    

    @FXML
    private void editReclamation(ActionEvent event) {
        //EDIT HERE
        handleOk();
        
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
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
        sujet.setText(reclamation.getSujet());
        contenu.setText(reclamation.getContenu());
       
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
            reclamation.setSujet(sujet.getText());
            reclamation.setContenu(contenu.getText());
            reclamation.setEtat_reclamation(etat.getValue());
            ReclamationService rs = new ReclamationService();
            ReclamationReponse reponse = rs.recupererReponse(reclamation.getId());
            rs.modifier(reclamation);
            if(recItemController!=null)
            recItemController.setReclamation(reclamation, reponse);
            else if (recItemBackController !=null)
                recItemBackController.setReclamation(reclamation, reponse);

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

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (sujet.getText() == null || sujet.getText().length() == 0) {
            errorMessage += "Sujet non valide!\n"; 
        }
        if (contenu.getText() == null || contenu.getText().length() == 0) {
            errorMessage += "Contenu non valide!\n"; 
        }
        if (etat.getValue()== null)
        {
            errorMessage += "Etat reclamation non valide!\n";
        }
        
        

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("Corrigez les champs invalide pour continuer");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
}
    
    
    


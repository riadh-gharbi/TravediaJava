/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class AddReclamationBackController implements Initializable {

    @FXML
    private TextField sujet;
    @FXML
    private TextField contenu;

   private String previous;
    private DashboardController dashboardController;

    public DashboardController getDashboard1Controller() {
        return dashboardController;
    }

    public void setDashboard1Controller(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addReclamation(ActionEvent event) {
        Reclamation r = new Reclamation();
        r.setContenu(contenu.getText());
        r.setSujet(sujet.getText());
        r.setEtat_reclamation("En Cours");
        //PLACEHOLDER
        r.setUser_id(1);
        ReclamationService rs = new ReclamationService();
        rs.ajouter(r);
      
       FXMain.instance.Recuperer();
            //afficherRec = (AnchorPane) loader.load();
                   // FXmain.getRootLayout().setCenter(afficherRec);
                  //  ReclamationController reclamationController = loader.getController();
                 
        back();
                   // reclamationController.setFXMain(FXmain);
                 
                    

        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.show();
          
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
    
    @FXML
    private void goBack(ActionEvent event) {
        back();
    }

    
}

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.ReclamationService;
import util.Session;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class AddReclamationController implements Initializable {

    @FXML
    private TextField sujet;
    @FXML
    private TextField contenu;
    
    private String previous;
    private Dashboard1Controller dashboard1Controller;
    private FrontController frontController;
    
    public void setFrontController(FrontController frontController)
    {
        this.frontController = frontController;
    }

    public Dashboard1Controller getDashboard1Controller() {
        return dashboard1Controller;
    }

    public void setDashboard1Controller(Dashboard1Controller dashboard1Controller) {
        this.dashboard1Controller = dashboard1Controller;
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
        r.setUser_id(Session.getUser().getId());
        ReclamationService rs = new ReclamationService();
        rs.ajouter(r);
      
        
             
            //afficherRec = (AnchorPane) loader.load();
                   // FXmain.getRootLayout().setCenter(afficherRec);
                  //  ReclamationController reclamationController = loader.getController();
                    FXMain.instance.Recuperer();
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
//            dashboard1Controller.getRecCont().getChildren().clear();
//            dashboard1Controller.getRecCont().getChildren().add(root);
            frontController.getAnchorPane().getChildren().clear();
            frontController.getAnchorPane().getChildren().add(root);
            ListRecTestController listRecTestController = loader.getController();
            listRecTestController.setFrontController(frontController);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class DashboardController implements Initializable {
    private Parent test;
    //private BorderPane Center;
    @FXML
    private AnchorPane anchor;

    public AnchorPane getAnchor() {
        return anchor;
    }
    
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherUser();
    }    
    
    public void render(String url)
    {
    try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(url));
            test = loader.load();
            
            //if (Center.getCenter() == null)
            anchor.getChildren().clear();
            anchor.getChildren().add(test);
             AnchorPane.setTopAnchor(test,0.0);
                AnchorPane.setBottomAnchor(test,0.0);
                AnchorPane.setRightAnchor(test, -10.0);
                AnchorPane.setLeftAnchor(test, 5.0);
        } catch (IOException ex) {
            System.err.println("Error Render "+ ex.getMessage() + " "+ ex.getCause() + " " + Arrays.toString(ex.getSuppressed()));
        }
    }
    
   

    @FXML
    private void Reclamationlist(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("recListBack.fxml"));
            test = loader.load();
            
            //if (Center.getCenter() == null)
            anchor.getChildren().clear();
            anchor.getChildren().add(test);
             AnchorPane.setTopAnchor(test,0.0);
                AnchorPane.setBottomAnchor(test,0.0);
                AnchorPane.setRightAnchor(test, -10.0);
                AnchorPane.setLeftAnchor(test, 5.0);
        } catch (IOException ex) {
            System.err.println("Error List Rec "+ ex.getMessage() + " "+ ex.getCause() + " " + Arrays.toString(ex.getSuppressed()));
        }
    }

    @FXML
    private void PaiementList(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("listPayBack.fxml"));
            test = loader.load();
            ListPayBackController listPayBackController = loader.getController();
            listPayBackController.setDashboardController(this);
            //if (Center.getCenter() == null)
            anchor.getChildren().clear();
            anchor.getChildren().add(test);
            AnchorPane.setTopAnchor(test,0.0);
            AnchorPane.setBottomAnchor(test,0.0);
            AnchorPane.setRightAnchor(test, -10.0);
            AnchorPane.setLeftAnchor(test, 5.0);
        } catch (IOException ex) {
            System.err.println("Error List Pay "+ ex.getMessage() + " "+ ex.getCause() + " " + ex.getStackTrace().toString());
        }
    }

    @FXML
    private void userList(ActionEvent event) {
      afficherUser();
    }
    
    private void afficherUser()
    {
      try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ListUsers.fxml"));
            test = loader.load();
//            ListPayBackController listPayBackController = loader.getController();
//            listPayBackController.setDashboardController(this);
            //if (Center.getCenter() == null)
            anchor.getChildren().clear();
            anchor.getChildren().add(test);
            
            AnchorPane.setTopAnchor(test,0.0);
            AnchorPane.setBottomAnchor(test,0.0);
            AnchorPane.setRightAnchor(test, -10.0);
            AnchorPane.setLeftAnchor(test, 5.0);
        } catch (IOException ex) {
            System.err.println("Error List Pay "+ ex.getMessage() + " "+ ex.getCause() + " " + ex.getStackTrace().toString());
        }
    }

    @FXML
    private void destinationList(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ListeDestinationSimple.fxml"));
            test = loader.load();
//            ListPayBackController listPayBackController = loader.getController();
//            listPayBackController.setDashboardController(this);
            //if (Center.getCenter() == null)
            anchor.getChildren().clear();
            anchor.getChildren().add(test);
            AnchorPane.setTopAnchor(test,0.0);
            AnchorPane.setBottomAnchor(test,0.0);
            AnchorPane.setRightAnchor(test, -10.0);
            AnchorPane.setLeftAnchor(test, 5.0);
        } catch (IOException ex) {
            System.err.println("Error List Pay "+ ex.getMessage() + " "+ ex.getCause() + " " + ex.getStackTrace().toString());
        }
    }

    @FXML
    private void planningList(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ListePlanning.fxml"));
            test = loader.load();
//            ListPayBackController listPayBackController = loader.getController();
//            listPayBackController.setDashboardController(this);
            //if (Center.getCenter() == null)
            anchor.getChildren().clear();
            anchor.getChildren().add(test);
            AnchorPane.setTopAnchor(test,0.0);
            AnchorPane.setBottomAnchor(test,0.0);
            AnchorPane.setRightAnchor(test, -10.0);
            AnchorPane.setLeftAnchor(test, 5.0);
        } catch (IOException ex) {
            System.err.println("Error Planning "+ ex.getMessage() + " "+ ex.getCause() + " " + ex.getStackTrace().toString());
        }
    }

    @FXML
    private void eventList(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ShowCategorieSimple.fxml"));
            test = loader.load();
//            ListPayBackController listPayBackController = loader.getController();
//            listPayBackController.setDashboardController(this);
            //if (Center.getCenter() == null)
            anchor.getChildren().clear();
            anchor.getChildren().add(test);
            AnchorPane.setTopAnchor(test,0.0);
            AnchorPane.setBottomAnchor(test,0.0);
            AnchorPane.setRightAnchor(test, -10.0);
            AnchorPane.setLeftAnchor(test, 5.0);
        } catch (IOException ex) {
            System.err.println("Error List Pay "+ ex.getMessage() + " "+ ex.getCause() + " " + ex.getStackTrace().toString());
        }
    }

    @FXML
    private void postList(ActionEvent event) {
//          try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("listPayBack.fxml"));
//            test = loader.load();
//            ListPayBackController listPayBackController = loader.getController();
//            listPayBackController.setDashboardController(this);
//            //if (Center.getCenter() == null)
//            anchor.getChildren().clear();
//            anchor.getChildren().add(test);
//            AnchorPane.setTopAnchor(test,0.0);
//            AnchorPane.setBottomAnchor(test,0.0);
//            AnchorPane.setRightAnchor(test, -10.0);
//            AnchorPane.setLeftAnchor(test, 5.0);
//        } catch (IOException ex) {
//            System.err.println("Error List Pay "+ ex.getMessage() + " "+ ex.getCause() + " " + ex.getStackTrace().toString());
//        }
    }

    @FXML
    private void hotelList(ActionEvent event) {
       render("AfficherHotelSimple.fxml");
    }

    @FXML
    private void toFront(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Front.fxml"));
            Parent root = loader.load();
            
            FrontController fr = loader.getController();
            fr.renderProfile();
            anchor.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void signOut(ActionEvent event) {
        try {
            UtilisateurService us = new UtilisateurService();
            us.logout();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("user log.fxml"));
            Parent root = loader.load();
            anchor.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println("");
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Paiement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class ListPayBackController implements Initializable {

    
    @FXML
    private VBox vList;
    
       private DashboardController dashboardController;

   
    
    ObservableList<Paiement> listPay =  FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         dashboardController = FXMain.instance.getBackController();
        FXMain.instance.UpdatePaiement();
        listPay = FXMain.instance.getPaiementData();
        System.out.println(listPay.toString());
        
        for(Paiement p : listPay)
        {
            //Add entries in loop
            //if(r.getUser_id() == currentUserId)
            try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("payItemBack.fxml"));
            Parent root = loader.load();
            vList.getChildren().add(root);
            PayItemBackController payItemBackController = loader.getController();
            payItemBackController.setDashboardController(dashboardController);
            payItemBackController.setPayment(p);
            } catch (IOException | NullPointerException ex)
            {
                System.err.println("List Pay Error : " + ex.getMessage() + " " + ex.getCause());
                            System.err.println("Null Pointer List Payement Error :" + ex.getMessage() + "  "+ ex.getCause() + " " + ex.getStackTrace());

            }
        
        } 
    }    

    @FXML
    private void goAjoutRec(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AddPaiementBack.fxml"));
            Parent root = loader.load();
            if (dashboardController != null)
            {
                dashboardController.getAnchor().getChildren().clear();
                dashboardController.getAnchor().getChildren().add(root);
                
            }
            AddPaiementBackController addPaiementBackController= loader.getController();
            addPaiementBackController.setDashboardController(dashboardController);
            addPaiementBackController.setPrevious("listPayBack.fxml");

           
        } catch (IOException ex) {
            System.err.println("Error Ajout Pay button "+ ex.getMessage() + " "+ ex.getCause());
        }
    }
    
     public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
    
}

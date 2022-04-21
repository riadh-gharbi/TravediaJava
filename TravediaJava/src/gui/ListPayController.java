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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class ListPayController implements Initializable {

    
    private Dashboard1Controller dashboard1Controller;
    
    @FXML
    private VBox vList;

      ObservableList<Paiement> listPay =  FXCollections.observableArrayList();
       private DashboardController dashboardController;
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
        try{
        for(Paiement p: listPay){
            //Add entries in loop
            //if(r.getUser_id() == currentUserId)
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("payItemBack.fxml"));
                Parent root = loader.load();
                vList.getChildren().add(root);
                PayItemBackController payItemBackController = loader.getController();
                payItemBackController.setDashboardController(dashboardController);
                //RecItemBackController recItemBackController;
                //recItemBackController = loader.getController();
                //recItemController.setDashboard1Controller(dashboardController);
                //recItemController.setFxm(fxm);
                //recItemBackController.setReclamation(r, rs.recupererReponse(r.getId()));
                payItemBackController.setPayment(p);
            } catch (IOException ex)
            {
                System.err.println("List Pay Error : " + ex.getMessage() + " " + ex.getCause());
            }
        } }catch(NullPointerException ex)
        {
            System.err.println(ex.getCause());
        }
    }    

    @FXML
    private void goAjoutRec(ActionEvent event) {
        
    }
    
     public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
    
}

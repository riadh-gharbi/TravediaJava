/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXTextField;
import entities.Paiement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import util.Smsapi;

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
       
       private FrontController frontController;
    @FXML
    private JFXTextField searchInput;
       
       public void setFrontController(FrontController frontController)
       {
           this.frontController= frontController;
       }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Smsapi.sendSMS("Test sms");
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
    public boolean comparePayItems(String searchTerm, String user,String owner, String etat, String type,String dateC
    ,String dateP,String planning,String prix)
    {
        String regex = "^"+searchTerm;
        Pattern pattern = Pattern.compile(regex);
        Matcher userM, ownerM,etatM ,typeM,dateCM ,datePM ,planM , prixM ;
        if (searchTerm ==null) return true;
        userM =user !=null ?  pattern.matcher(user) : null;
        ownerM = owner!=null?pattern.matcher(owner):null;
        etatM = etat!=null? pattern.matcher(etat) :null;
        typeM = type!=null? pattern.matcher(type) :null;
        dateCM = dateC!=null? pattern.matcher(dateC) :null;
       
        datePM = dateP!=null? pattern.matcher(dateP) :null;
        planM = planning!=null? pattern.matcher(planning) :null;
        prixM = prix!=null? pattern.matcher(prix) :null;
        boolean returnValue= userM.find()|| ownerM.find()|| etatM.find() || typeM.find()
                || dateCM.find() || datePM.find() || planM.find()
                || prixM.find();
        return returnValue;
    
    }
    
     public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    @FXML
    private void textAction(KeyEvent event) {
        
        String searchTerm = searchInput.getText();
        if (searchTerm ==null) return ;
        vList.getChildren().clear();
        FXMain.instance.UpdatePaiement();
        listPay = FXMain.instance.getPaiementData();
        try{
        for(Paiement p: listPay){
            //Add entries in loop
            //if(r.getUser_id() == currentUserId)
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("payItemBack.fxml"));
                Parent root = loader.load();
                System.out.println(p.toString());
                if(comparePayItems(searchTerm.toLowerCase(), String.valueOf(p.getClientId()).toLowerCase()
                        , String.valueOf(p.getOwnerId()).toLowerCase()
                        , p.getStatut().toLowerCase()
                        , p.getType_paiement().toLowerCase()
                        , p.getDate_creation().toString().toLowerCase()
                        , p.getDate_paiement()!=null?p.getDate_paiement().toString().toLowerCase():""
                        , String.valueOf(p.getPlanningId()).toLowerCase()
                        , String.valueOf(p.getPrix()).toLowerCase())){
                    
                vList.getChildren().add(root);
                PayItemBackController payItemBackController = loader.getController();
                //payItemBackController.setDashboardController(dashboardController);
                
                payItemBackController.setPayment(p);}
            } catch (IOException ex)
            {
                System.err.println("List Pay Error : " + ex.getMessage() + " " + ex.getCause());
            }
        } }catch(NullPointerException ex)
        {
            System.err.println("null pointer : "+ex.getCause());
        }
    }
    public void addItem(Paiement pay)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("payItemBack.fxml"));
            Parent root = loader.load();
            vList.getChildren().add(root);
            PayItemBackController payItemBackController = loader.getController();
            payItemBackController.setPayment(pay);
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
                
    
    }
    @FXML
    private void versPaiement(ActionEvent event) {
        FXMain.instance.showPayDialog(this);
    }
    
}

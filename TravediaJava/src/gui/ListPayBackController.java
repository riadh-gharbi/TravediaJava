/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXTextField;
import entities.Paiement;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import services.PaiementService;
import services.UtilisateurService;
import util.Session;

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
    @FXML
    private JFXTextField searchInput;
    
    private UtilisateurService us;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        us = new UtilisateurService();
        PaiementService ps = new PaiementService();
         dashboardController = FXMain.instance.getBackController();
        FXMain.instance.UpdatePaiement();
        if(us.checkRole(Session.getUser()).equals("admin"))
        {
            listPay = FXMain.instance.getPaiementData();
        }else if(us.checkRole(Session.getUser()).equals("guide")){
        listPay.addAll(ps.recupererParOwner(Session.getUser().getId()));
        }else {listPay.addAll(ps.recupererParUser(Session.getUser().getId()));}
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
            
                dashboardController.getAnchor().getChildren().clear();
                dashboardController.getAnchor().getChildren().add(root);
                
            
            AddPaiementBackController addPaiementBackController= loader.getController();
            addPaiementBackController.setDashboardController(dashboardController);
            addPaiementBackController.setPrevious("listPayBack.fxml");

           
        } catch (IOException ex) {
            System.err.println("Error Ajout Pay button "+ ex.getMessage() + " "+ ex.getCause());
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
                String clientName,ownerName;
                if (us.recuperer(p.getClientId())!=null)
                {
                    clientName = us.recuperer(p.getClientId()).getPrenom() + " " + 
                            us.recuperer(p.getClientId()).getNom();
                }
                else {clientName = String.valueOf(p.getClientId()).toLowerCase();}
                if (us.recuperer(p.getOwnerId())!=null)
                {
                    ownerName = us.recuperer(p.getOwnerId()).getPrenom() + " " + 
                            us.recuperer(p.getOwnerId()).getNom();
                }
                else {ownerName = String.valueOf(p.getOwnerId()).toLowerCase();}
                if(comparePayItems(searchTerm.toLowerCase(), clientName.toLowerCase()
                        , ownerName.toLowerCase()
                        , p.getStatut().toLowerCase()
                        , p.getType_paiement().toLowerCase()
                        , p.getDate_creation().toString().toLowerCase()
                        , p.getDate_paiement()!=null?p.getDate_paiement().toString().toLowerCase():""
                        , String.valueOf(p.getPlanningId()).toLowerCase()
                        , String.valueOf(p.getPrix()).toLowerCase())){
                    System.out.println(p.toString());
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
     public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
    
}

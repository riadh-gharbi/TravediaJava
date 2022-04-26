/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.PaymentLinkCollection;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import entities.Paiement;
import entities.Planning;
import entities.Utilisateur;
import java.net.URI;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import services.PaiementService;
import services.PlanningService;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class PayController implements Initializable {

    @FXML
    private JFXButton pay;
    
     private ListPayController payController;
     
     private Paiement p;
    @FXML
    private ChoiceBox<Planning> planChoice;
    @FXML
    private ChoiceBox<Utilisateur> clientChoice;
    
    private List<Utilisateur> userList = new ArrayList<>();
    private ObservableList<Utilisateur> userObsList = FXCollections.observableArrayList();
    private List<Planning> planList = new ArrayList<>();
    private ObservableList<Planning> planObsList= FXCollections.observableArrayList();
    
    public void setListPayController(ListPayController payController){
        this.payController = payController;
    }
    
    
     private Stage dialogStage;
    private boolean okClicked = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Initialize User and planning choice boxes
        
        //User choice Box
        UtilisateurService us = new UtilisateurService();
        userList = us.recuperer();
        
        for (Utilisateur u:userList)
        {
            userObsList.add(u);
        }
        clientChoice.setItems(userObsList);
        clientChoice.setConverter(new StringConverter<Utilisateur>(){
            @Override
            public String toString(Utilisateur u)
            {
                return u.getNom() + " " + u.getPrenom();
            }
            
            @Override
            public Utilisateur fromString(String string)
            {
                return clientChoice.getItems().stream().filter(u->(u.getId()==Integer.parseInt(string))).findFirst().orElse(null);
            }
        
        });
        clientChoice.valueProperty().addListener((obs,oldval,newval)->{
            if(newval !=null){
                System.out.println("Selected User is: " + newval.getNom() + " ID: "+ newval.getId());
            }
        });
        
        //Plan choice box
        PlanningService ps = new PlanningService();
        planList = ps.recuperer();
        for(Planning p:planList)
        {
            planObsList.add(p);
        }
        planChoice.setItems(planObsList);
        planChoice.setConverter(new StringConverter<Planning>() {
            @Override
            public String toString(Planning object) {
                return "Planning"+us.recuperer(object.getVoyageurId()).getNom();
            }

            @Override
            public Planning fromString(String string) {
                return planChoice.getItems().stream().filter(p->p.getId()==Integer.parseInt(string)).findFirst().orElse(null);
            }
        });
        planChoice.valueProperty().addListener((obs,oldval,newval)->
        {
            if (newval !=null)
            {
                System.out.println("Selected Planning is :"+newval.getId());
            }
        });
        
        
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

    @FXML
    private void pay(ActionEvent event) throws StripeException {
        
         p=   new Paiement( 7, 1, 4,1000, "En Cours", new Date(System.currentTimeMillis()), null, "En Ligne", null);
        
        Stripe.apiKey = "sk_test_51KT8ejAISKORykYshnnbQcDPyMdeStYUi7Xtp05Lh1or86C6AHB8K3NsvA6CmiFXv4obHCq1p3gxp8oV8YHNizZ000pllSDFVs";

       
        
        

Map<String, Object> productParams = new HashMap<>();
productParams.put("name", "Planning "+planChoice.getValue().getId());
Product product = Product.create(productParams);    

Map<String, Object> recurring = new HashMap<>();
recurring.put("interval", "month");
Map<String, Object> priceParams = new HashMap<>();
priceParams.put("unit_amount", planChoice.getValue().getPrix());
priceParams.put("currency", "eur");
priceParams.put("recurring", recurring);
priceParams.put("product", product.getId());

Price price = Price.create(priceParams);
                 List<Object> lineItems = new ArrayList<>();
        Map<String, Object> lineItem1 = new HashMap<>();
        lineItem1.put(
          "price",
          price.getId()
        );
//        Map<String,Object> metadata = new HashMap<>();
//        metadata.put("id",p.getId() );
//        lineItem1.put("quantity", 1);
        
//        lineItems.add(lineItem1);
//        Map<String, Object> params = new HashMap<>();
//        params.put("line_items", lineItems);
//        params.put("metadata",metadata);

        PaymentLinkCreateParams paymentLinkCreateParams = PaymentLinkCreateParams.builder()
                
                .addLineItem(PaymentLinkCreateParams.LineItem.builder()
                
                .setPrice(String.valueOf(p.getPrix()))
                .setQuantity(1L)
                        .build()).build();
        PaymentLink paymentLink =
          PaymentLink.create(paymentLinkCreateParams);
        
        //Session session = Session.create(param);
        //StripeResponse response = new StripeResponse(session.getId());
       
        //Redirect to URL
            String url =paymentLink.getUrl();

          try {

           URI uri= new URI(paymentLink.getUrl());
              
           java.awt.Desktop.getDesktop().browse(uri);
           System.out.println("Web page opened in browser");
//           Paiement p = new Paiement(planChoice.getValue().getVoyageurId()
//                   , clientChoice.getValue().getId()
//                   , planChoice.getValue().getId(),
//                   planChoice.getValue().getPrix()
//                   , "En Cours", new Date(), date_paiement, url, url);
          } catch (Exception e) {

           e.printStackTrace();
          }
    }

    private void listPay(ActionEvent event) {
        try {
            Stripe.apiKey = "sk_test_51KT8ejAISKORykYshnnbQcDPyMdeStYUi7Xtp05Lh1or86C6AHB8K3NsvA6CmiFXv4obHCq1p3gxp8oV8YHNizZ000pllSDFVs";
            
            Map<String, Object> params = new HashMap<>();
            params.put("limit", 3);
            
            PaymentLinkCollection paymentLinks =
                    PaymentLink.list(params);
            System.out.println(paymentLinks.toString());
        } catch (StripeException ex) {
            System.err.println(ex.getMessage());
        }
    }


    void setPaiement(Paiement paiement) {
        p = paiement;
    }

    
}

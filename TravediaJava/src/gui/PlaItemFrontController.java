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
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import entities.Paiement;
import entities.Planning;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import services.PaiementService;
import services.PlanningService;
import services.UtilisateurService;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Razer
 */
public class PlaItemFrontController implements Initializable {

   
    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private Label datedepart;
    @FXML
    private Label datefin;
    @FXML
    private Label prix;
    @FXML
    private Label destination;
    @FXML
    private Label typeplan;
    @FXML
    private Label evenement;
    @FXML
    private Label hotel;
    @FXML
    private JFXButton modify;
    @FXML
    private JFXButton delete;
    Planning pp;
    ListePlanningFrontController controller;
    @FXML
    private JFXButton pay;
    
    private UtilisateurService us;
    private PaiementService payService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        us = new UtilisateurService();
        payService = new PaiementService();
        modify.setText("Modifier");
        delete.setText("Supprimer");
        pay.setText("Payer");
    }    
 public void setplanning(Planning p){
     pp = p;
    nom.setText(String.valueOf(p.getVoyageurId()));
     System.out.println("VOYAGEUR ID: "+String.valueOf(p.getVoyageurId()));
    description.setText(p.getDescription());
    datedepart.setText(p.getDateDepart().toString());
    datefin.setText(p.getDateFin().toString());
    prix.setText(String.valueOf(p.getPrix()));
    destination.setText(String.valueOf(p.getDestinationId()));
    typeplan.setText((p.getTypePlan()));
    evenement.setText(String.valueOf(p.getEvenementId()));
    hotel.setText(String.valueOf(p.getHotelId()));
 
 
 }
 public void setParentController(ListePlanningFrontController contr)
    {
        controller = contr;
    }
    @FXML
    private void modpla(ActionEvent event) {
     try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ListePlanningFront.fxml"));
            Parent root = loader.load();
            ModifierPlanningController editho = loader.getController();
            editho.setPlanning(pp);
           nom.getScene().setRoot(root);
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void deletepla(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Paiement");
        alert.setHeaderText("Attention , Vous allez supprimer un paiement!");
        alert.setContentText("Voulez vous vraiment supprimer ce paiement?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            PlanningService ps = new PlanningService();
            //System.out.println(paiement.getId());
            ps.supprimer(pp.getId());
                   } else {
            alert.close();
        }
    }

     @FXML
    private void pay(ActionEvent event) throws StripeException {
        
 //Add paiement to database
            String userName;
            if(us.recuperer(pp.getVoyageurId())!=null)
            {
                userName = us.recuperer(pp.getVoyageurId()).getPrenom() + " "+ 
                        us.recuperer(pp.getVoyageurId()).getNom();
                        
            }else
            {
                userName = String.valueOf(pp.getVoyageurId());
            }
           Paiement p = new Paiement(pp.getVoyageurId()
                   , Session.getUser().getId()
                   , pp.getId(),
                   pp.getPrix()
                   , "En Cours", new Date(System.currentTimeMillis()), null,"En Ligne", null);
           
          
           
           payService.ajouter(p);
           p = payService.recupererLast();
        Stripe.apiKey = "sk_test_51KT8ejAISKORykYshnnbQcDPyMdeStYUi7Xtp05Lh1or86C6AHB8K3NsvA6CmiFXv4obHCq1p3gxp8oV8YHNizZ000pllSDFVs";

       
        
        

Map<String, Object> productParams = new HashMap<>();
productParams.put("name", "Planning "+ pp.getId());
Product product = Product.create(productParams);    


Map<String, Object> priceParams = new HashMap<>();
priceParams.put("unit_amount", pp.getPrix());
priceParams.put("currency", "eur");
priceParams.put("product", product.getId());
Map<String, String> initialMetadata = new HashMap<>();
initialMetadata.put("id",String.valueOf( p.getId()));
priceParams.put("metadata", initialMetadata);

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
                
                .setPrice(price.getId())
                        
                .setQuantity(1L)
                        
                        .build()
                )
                .setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                .setType(PaymentLinkCreateParams.AfterCompletion.Type.HOSTED_CONFIRMATION)
                        .setHostedConfirmation(new PaymentLinkCreateParams.AfterCompletion.HostedConfirmation.Builder().setCustomMessage("Paid").build()).build())
              
                .build();
        PaymentLink paymentLink =
          PaymentLink.create(paymentLinkCreateParams);
        
       
       
        //Redirect to URL
            String url =paymentLink.getUrl();

          try {

           URI uri= new URI(paymentLink.getUrl());
              
           java.awt.Desktop.getDesktop().browse(uri);
           System.out.println("Web page opened in browser");
           
          
           
            //add to list
            //payController.addItem(p);
          } catch (Exception e) {

           e.printStackTrace();
          }
    }
    }
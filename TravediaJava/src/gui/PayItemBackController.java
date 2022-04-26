/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import entities.Paiement;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.PaiementService;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class PayItemBackController implements Initializable {

    @FXML
    private Label client;
    @FXML
    private Label guide;
    @FXML
    private Label prix;
    @FXML
    private Label etat;
    @FXML
    private Label type;
    @FXML
    private Label stripe;
    @FXML
    private Label dateCreation;
    @FXML
    private Label datePaiement;
    @FXML
    private JFXButton modify;
    @FXML
    private JFXButton delete;
    @FXML
    private Label planning;
    
    private Image modifyImage;
    
    private ImageView deleteImage;
    
    private FXMain fxm;
    
    private DashboardController dashboardController;
    
    
    Paiement paiement;
    

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
    
    public void setPayment (Paiement p)
    {
        paiement =p;
        planning.setText(String.valueOf(p.getPlanningId()));
        client.setText(String.valueOf(p.getClientId()));
        guide.setText(String.valueOf(p.getOwnerId()));
        prix.setText(String.valueOf(p.getPrix()));
        etat.setText(p.getStatut());
        type.setText(p.getType_paiement());
        if (stripe.getText().length() ==0)
        stripe.setText(p.getSessionID());
        else stripe.setText("Null");
        dateCreation.setText(p.getDate_creation().toString());
        if (datePaiement.getText().length()== 0)
        datePaiement.setText(p.getDate_paiement().toString());
        else 
            datePaiement.setText("Null");
        
        
    
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         fxm = FXMain.instance;
        // TODO
        //modify.setMaxSize(52, 25);
        modifyImage = new Image(getClass().getResourceAsStream("images/icons8-edit-64.png"),52,25,true,true);
        ImageView imageView = new ImageView(modifyImage);
        modify.setGraphic(imageView);
       
        deleteImage = new ImageView (getClass().getResource("images/icons8-delete-24.png").toExternalForm());
        delete.setGraphic(deleteImage);
    }    

    @FXML
    private void editPay(ActionEvent event) {
        fxm.showPayEditBackDialog(paiement,this);
    }

    @FXML
    private void deletePay(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Paiement");
        alert.setHeaderText("Attention , Vous allez supprimer un paiement!");
        alert.setContentText("Voulez vous vraiment supprimer ce paiement?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            PaiementService ps = new PaiementService();
            //System.out.println(paiement.getId());
            ps.supprimer(paiement.getId());
            fxm.UpdatePaiement();
        } else {
            alert.close();
        }
    }
    
}

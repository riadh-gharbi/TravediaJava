/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import entities.Reclamation;
import entities.ReclamationReponse;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.ReclamationService;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class RecItemBackController implements Initializable {

    @FXML
    private Label sujet;
    @FXML
    private Label contenu;
    @FXML
    private Label etat;
    @FXML
    private Label user;
    @FXML
    private Label rep;
    @FXML
    private JFXButton modify;
    @FXML
    private JFXButton delete;
    private Image modifyImage;
    
    private ImageView deleteImage;
    
    private Reclamation rec;
    private ReclamationReponse reponse;
    
    private FXMain fxm;

    public void setReclamation(Reclamation r, ReclamationReponse reponse)
    {
        UtilisateurService us = new UtilisateurService();
        sujet.setText(r.getSujet());
        contenu.setText(r.getContenu());
        etat.setText(r.getEtat_reclamation());
         if(us.recuperer(r.getUser_id()) != null)
        user.setText(us.recuperer(r.getUser_id()).getPrenom()+ " " +us.recuperer(r.getUser_id()).getNom());
        else user.setText(r.getUser_id().toString());
        rep.setText(reponse.getContenu());
        rec = r;
        this.reponse = reponse;
        
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
        ColorAdjust colorAdjust = new ColorAdjust();
        //Setting the contrast value 
      colorAdjust.setContrast(0.4);     
      
      //Setting the hue value 
      colorAdjust.setHue(-0.05);     
      
      //Setting the brightness value 
      colorAdjust.setBrightness(0.9);  
      
      //Setting the saturation value 
      colorAdjust.setSaturation(0.8);   
      imageView.setEffect(colorAdjust);
        modify.setGraphic(imageView);
       
        deleteImage = new ImageView (getClass().getResource("images/icons8-delete-24.png").toExternalForm());
        deleteImage.setEffect(colorAdjust);
        delete.setGraphic(deleteImage);
    }    

    @FXML
    private void editRec(ActionEvent event) {
        //open the edit wiindow
        fxm.showRecEditBackDialog(rec,this);
    }

    @FXML
    private void deleteRec(ActionEvent event) {
        //open delete dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Suppression Reclamation");
alert.setHeaderText("Attention , Vous allez supprimer une reclamation!");
alert.setContentText("Voulez vous vraiment supprimer cette reclamation?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    ReclamationService rs = new ReclamationService();
    rs.supprimer(rec.getId());
} else {
    alert.close();
}
    }

    @FXML
    private void respondRec(ActionEvent event) {
        //open response dialog
        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("R??pondre ?? la r??clamation");
        dialog.setHeaderText("R??pondre ?? la r??clamation");
        dialog.setContentText("Veuillez ins??rer votre r??ponse");
        ReclamationService rs = new ReclamationService();

        Optional<String> result = dialog.showAndWait();

        ReclamationReponse newRep = new ReclamationReponse();

        result.ifPresent(contenuRep ->{ newRep.setContenu(contenuRep);
        rs.ajouterReponse(newRep, rec.getId());
        rep.setText(newRep.getContenu());
        FXMain.instance.Recuperer();
        } );
        
    }
    
}

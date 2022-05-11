/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import entities.Reclamation;
import entities.ReclamationReponse;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.ReclamationService;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class RecItemController implements Initializable {

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

    public Label getSujet() {
        return sujet;
    }

    public Label getContenu() {
        return contenu;
    }

    public Label getEtat() {
        return etat;
    }

    public Label getUser() {
        return user;
    }

    public Label getRep() {
        return rep;
    }
    
    private Dashboard1Controller dashboard1Controller;
    
    private FXMain fxm;
    Reclamation rec;
    ReclamationReponse reponse;

    public FXMain getFxm() {
        return fxm;
    }

    public void setFxm(FXMain fxm) {
        this.fxm = fxm;
    }

    public void setDashboard1Controller(Dashboard1Controller dashboard1Controller) {
        this.dashboard1Controller = dashboard1Controller;
    }
    
   
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        //System.out.println("FXM IS HERE :"+ getFxm());
    }    
    
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

    @FXML
    private void editRec(ActionEvent event) {
        fxm.showRecEditDialog(rec,this);
    }

    @FXML
    private void deleteRec(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
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
    
    
    public boolean showPersonEditDialog(Reclamation reclamation) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EditReclamation.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(FXMain.primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        EditReclamationController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setReclamation(rec);
        controller.setDashboard1Controller(dashboard1Controller);
        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        System.err.println("error dialog "+e.getMessage()+" "+ e.getCause());
        return false;
    }
}
    
}

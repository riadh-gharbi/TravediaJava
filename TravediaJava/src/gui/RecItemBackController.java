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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.ReclamationService;

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
        sujet.setText(r.getSujet());
        contenu.setText(r.getContenu());
        etat.setText(r.getEtat_reclamation());
        user.setText(r.getUser_id().toString());
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
        modify.setGraphic(imageView);
       
        deleteImage = new ImageView (getClass().getResource("images/icons8-delete-24.png").toExternalForm());
        delete.setGraphic(deleteImage);
    }    

    @FXML
    private void editRec(ActionEvent event) {
        //open the edit wiindow
        fxm.showRecEditBackDialog(rec);
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
        dialog.setTitle("Répondre à la réclamation");
        dialog.setHeaderText("Répondre à la réclamation");
        dialog.setContentText("Veuillez insérer votre réponse");
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

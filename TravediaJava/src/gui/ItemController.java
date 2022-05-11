/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ItemController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label image;
    Categorie currentCategorie;
    ShowCategorieController controller;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public ItemController()
    {
      //  controller = contr;
    }
    public void setCategorie(Categorie cat){
        currentCategorie = cat;
        nom.setText(cat.getNom());
        if(cat.getImage()==null)image.setText("None");
        else image.setText(cat.getImage());
        
    }
    public void setParentController(ShowCategorieController contr)
    {
        controller = contr;
    }
    @FXML
    private void edit(ActionEvent event) throws IOException {
        try{
            
           FXMLLoader loader = new FXMLLoader(getClass().getResource("EditCategorieSimple.fxml"));
            Parent root = loader.load();
            EditCategorieController editc = loader.getController();
            editc.setCategorie(currentCategorie);
           FXMain.instance.getBackController().getAnchor().getChildren().clear();
                      FXMain.instance.getBackController().getAnchor().getChildren().add(root);
                      AnchorPane.setTopAnchor(root,0.0);
                AnchorPane.setBottomAnchor(root,0.0);
                AnchorPane.setRightAnchor(root, -10.0);
                AnchorPane.setLeftAnchor(root, 5.0);

        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void delete(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you Sure ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                
                 //notif
        Notifications notificationBuilder = Notifications.create()
                .title("Confirmation")
                .text("Deleted successfully !!")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("notiff");
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        notificationBuilder.showConfirm();
                
                new CategorieService().supprimer(currentCategorie);
                controller.refreshList();
            }
        }
    }


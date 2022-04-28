/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Destination;
import entities.Region;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.management.Notification;
import services.DestinationService;
import services.RegionService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class ModifierDestinationController implements Initializable {
Destination currentDestination;
    AfficherDestinationController controller;
    @FXML
    private Button btn;
    @FXML
    private TextField nom;
    @FXML
    private Button btnup;
    @FXML
    private ImageView viewimage;
    @FXML
    private TextField imagepath;
    @FXML
    private TextArea desc;
    @FXML
    private ComboBox<String> combox;
    final FileChooser fc = new FileChooser();
private String path ; 
File selectedFile;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   public void setDestination(Destination r){
        currentDestination = r;
        nom.setText(r.getNom());
        desc.setText(r.getDescription());
    }
    @FXML
    private void handleuploadimage(ActionEvent event) {
        fc.setTitle("my uploaded image");
        fc.setInitialDirectory(new File (System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif","*.jpeg"));
//        File file =fc.showOpenDialog(null);
//        if (file != null){
//            path = selectedFile.getName();
//   
//            viewimage.setImage(new Image(file.toURI().toString()));
//        }else {
//            System.out.println("image invalide");
//        }
   selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {

            path = selectedFile.getName();
//    
            imagepath.setText(path);
            Image imagea = new Image(selectedFile.toURI().toString());
           viewimage.setImage(imagea) ;

    }}
@FXML
    private void editr(ActionEvent event) throws IOException {
       System.out.println("1");
        currentDestination.setNom(nom.getText());
        currentDestination.setDescription(desc.getText());

      currentDestination.setImage(path);
       // r.setImage(path);
        System.out.println("2");
        //currentRegion.setImage(path);
         TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            
            tray.setAnimationType(type);
            tray.setTitle("GOOD");
            tray.setMessage("La destination a été modifier");
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));
           
        DestinationService rs = new DestinationService();
        System.out.println("3");
        rs.modifier(currentDestination);
        System.out.println("4");
        Parent root = FXMLLoader.load(getClass().getResource("AfficherDestination.fxml"));
        nom.getScene().setRoot(root);
    } 

    @FXML
    private void opendest(ActionEvent event) {
    }

   
}

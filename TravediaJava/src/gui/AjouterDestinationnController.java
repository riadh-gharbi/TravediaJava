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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import services.DestinationService;
import services.RegionService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FilenameUtils;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class AjouterDestinationnController implements Initializable {

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
final FileChooser fc = new FileChooser();
private String path ; 
File selectedFile;
Connection cnx ;
    
    @FXML
    private ComboBox<String> combox;
     private ObservableList<String> stationsList = FXCollections.observableArrayList();
    List<Region> listregion;
    private Button btndest;
     private boolean verificationImage=true;
    @FXML
    private TextField latitudetxt;
    @FXML
    private TextField longitudetxt;
 
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
listregion = new RegionService().recuperer();
        for (Region region : listregion) {
            combox.getItems().add(region.getNom());
    }    }

    @FXML
    private void AjouterDestination(ActionEvent event) throws IOException {
         int error = 0;

        if (nom.getText().isEmpty()) {
            nom.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez ajouter un nom ! ");
            al.showAndWait();
       
            error++;
//            Parent root = FXMLLoader.load(getClass().getResource("AjouterDestinationSimple.fxml"));
//               nom.getScene().setRoot(root);
            FXMain.instance.getBackController().render("AjouterDestinationSimple.fxml");
        } else {
            nom.setStyle(null);
        }
        
          if (desc.getText().isEmpty()) {
            desc.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez ajouter un description! ");
            al.showAndWait();
            error++;
//             Parent root = FXMLLoader.load(getClass().getResource("AjouterDestinationSimple.fxml"));
//               nom.getScene().setRoot(root);
            FXMain.instance.getBackController().render("AjouterDestinationSimple.fxml");
        } else {
            desc.setStyle(null);
        }
//           if(combox.getSelectionModel().getSelectedIndex() == -1)
//       {
//           System.out.println("please select a region");
//         //  errorCateg.setVisible(true);
//           return;
//       }
//       else
//        {
//             System.out.println("else what");
// errorCateg.setVisible(false);
           // checkCateg.setVisible(true);
        
      //  System.out.println("selected combox is " + listcat.get(combox.getSelectionModel().getSelectedIndex()));
         
    
        //String fileName = "";
//        try {
//            fileName = System.currentTimeMillis() + "." + File.getExtension(selectedFile.getName());
//            File destFile = new File(imageDir + "/" + fileName);
//          //  FileUtils.copyFile(selectedFile, destFile);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
 
        
         Destination r = new Destination();
        r.setNom(nom.getText());
        r.setDescription(desc.getText());
        r.setImage(path);
        r.setId_region(listregion.get(combox.getSelectionModel().getSelectedIndex()).getId());
        
        r.setLatitude(latitudetxt.getText());
        r.setLongitude(longitudetxt.getText());
     //  r.setRegion(combox.getValue());
      //  r.setRegion(combox.getSelectionModel().getSelectedItem());
       TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            
            tray.setAnimationType(type);
            tray.setTitle("SUCCESS");
            tray.setMessage("une nouvelle destination a été ajouté");
            tray.setNotificationType(NotificationType.SUCCESS);//
            tray.showAndDismiss(Duration.millis(3000));
          //  tray.setImage(img);
          //  tray.setTrayIcon(img);
          
        DestinationService ps= new DestinationService();
        System.out.println("HAHAHAHAHAHAHAHAHAHAHA");
        ps.ajouter(r);
//         Parent root = FXMLLoader.load(getClass().getResource("AfficherDestinationSimple.fxml"));
//               nom.getScene().setRoot(root);
            FXMain.instance.getBackController().render("ListeDestinationSimple.fxml");
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

        }
    }

      private void opendest(ActionEvent event) throws IOException {
        
          Parent root = FXMLLoader.load(getClass().getResource("AfficherDestination.fxml"));
               btndest.getScene().setRoot(root);
    }
    
}

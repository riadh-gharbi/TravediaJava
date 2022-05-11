/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Region;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;
import services.RegionService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class AjouterRegionController implements Initializable {

    @FXML
    private Button btnup;
    @FXML
    private ImageView viewimage;
    @FXML
    private Button btn;
    @FXML
    private TextField nom;
final FileChooser fc = new FileChooser();
private String path ; 
File selectedFile;
    @FXML
    private TextField imagepath;
    @FXML
    private Label nomimg;
   
     public static Region region = null;
      String imagelink = "";
  // public static String imageDir = "C:\\xampp\\htdocs\\Travedia\\Travedia\\public\\uploads\\brochures";
   public static String imageDir = "C:\\Users\\Ameni\\Documents\\GitHub\\TravediaJava\\TravediaJava\\src\\images\\images";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//         if (region != null) {
//            imagelink = region.getImage();
//            System.out.println(imagelink);
//          
//            nomimg.setText(region.getImage());
//            Image imr = new Image(getClass().getResource("C:\\xampp\\htdocs\\Travedia\\Travedia\\public\\uploads\\brochures" + region.getImage()).toString());
//            viewimage.setImage(imr);
            
            
      //  }
    }    

    @FXML
    private void handleuploadimage(ActionEvent event) throws MalformedURLException {
//   FileChooser fileChooser = new FileChooser();
//        Window primaryStage = null;
//        File selectedFile = fileChooser.showOpenDialog(primaryStage);
//
//       // imagelink = selectedFile.toURI().toURL().toString().substring(selectedFile.toURI().toURL().toString().lastIndexOf("/") + 1);
//        imagelink = selectedFile.toURI().toURL().toString();
//        Image imr = new Image(getClass().getResource("C:\\xampp\\htdocs\\Travedia\\Travedia\\public\\uploads\\brochures" + imagelink).toString());
//       path = selectedFile.getName();
//////    
//          imagepath.setText(path);
//        viewimage.setImage(imr);
     
        //****************my old code*****************************************
//        fc.setTitle("my uploaded image");
//        fc.setInitialDirectory(new File (System.getProperty("user.home")));
//        fc.getExtensionFilters().clear();
//        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif","*.jpeg"));
////        File file =fc.showOpenDialog(null);
////        if (file != null){
////            path = selectedFile.getName();
////   
////            viewimage.setImage(new Image(file.toURI().toString()));
////        }else {
////            System.out.println("image invalide");
////        }
//   selectedFile = fc.showOpenDialog(null);
//        if (selectedFile != null) {
//
//            path = selectedFile.getName();
////    
//            imagepath.setText(path);
//            Image imagea = new Image(selectedFile.toURI().toString());
//           viewimage.setImage(imagea) ;
//
//    }
FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") ));
        fc.setTitle("Veuillez choisir l'image");
//        fc.getExtensionFilters().addAll(
//                //new FileChooser.ExtensionFilter("Image", ".jpg", ".png"),
//                new FileChooser.ExtensionFilter("PNG", "*.png"),
//                new FileChooser.ExtensionFilter("JPG", "*.jpg")
//        );
 fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif","*.jpeg"));
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
//    
            imagepath.setText(path);
            Image imagea = new Image(selectedFile.toURI().toString());
           viewimage.setImage(imagea) ;

        }
}

    @FXML
    private void AjouterRegion(ActionEvent event) throws IOException {
        
        int error = 0;

        if (nom.getText().isEmpty() ) {
            nom.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez ajouter un nom ! ");
            al.showAndWait();
       
            error++;
//            Parent root = FXMLLoader.load(getClass().getResource("AjouterRegionSimple.fxml"));
//               nom.getScene().setRoot(root);
            FXMain.instance.getBackController().render("AjouterRegionSimple.fxml");
        } else {
            nom.setStyle(null);
        }
         
    
         Region r = new Region();
             String fileName = "";
        try {
            fileName = System.currentTimeMillis() + "." + FilenameUtils.getExtension(selectedFile.getName());
            File destFile = new File(imageDir + "/" + fileName);
            FileUtils.copyFile(selectedFile, destFile);
        } catch (IOException |NullPointerException ex) {
            ex.printStackTrace();
        }
        r.setImage(fileName);
        r.setNom(nom.getText());
          r.setImage(path);
       // r.setImage(path);
       TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            
            tray.setAnimationType(type);
            tray.setTitle("SUCCESS");
            tray.setMessage("Une nouvelle region a été ajoutée");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
       RegionService ps= new RegionService();
        ps.ajouter(r);
//          Parent root = FXMLLoader.load(getClass().getResource("AfficherRegion.fxml"));
//               nom.getScene().setRoot(root);
        FXMain.instance.getBackController().render("ListeRegionBackSimple.fxml");
    }


   

 

   
   
}
    


//
//    @FXML
//    private void upload(ActionEvent event) throws MalformedURLException {
//      //  System.out.println(categorie.getValue());
//        FileChooser fc = new FileChooser();
//        fc.setInitialDirectory(new File(System.getProperty("user.home") ));
//        fc.setTitle("Veuillez choisir l'image");
//        fc.getExtensionFilters().addAll(
//                //new FileChooser.ExtensionFilter("Image", ".jpg", ".png"),
//                new FileChooser.ExtensionFilter("PNG", "*.png"),
//                new FileChooser.ExtensionFilter("JPG", "*.jpg")
//        );
//        selectedFile = fc.showOpenDialog(null);
//
//        if (selectedFile != null) {
//
//            path = selectedFile.getName();
////    
//          //  image.setText(path);
//            Image imagea = new Image(selectedFile.toURI().toString());
//           viewimage.setImage(imagea) ;
//
//        }
//
//    }
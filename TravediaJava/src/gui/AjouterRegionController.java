/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import services.RegionService;

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
   
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void AjouterRegion(ActionEvent event) throws IOException {
        
        int error = 0;

        if (nom.getText().isEmpty() ) {
            nom.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez ajouter un nom ! ");
            al.showAndWait();
       
            error++;
            Parent root = FXMLLoader.load(getClass().getResource("AjouterRegion.fxml"));
               nom.getScene().setRoot(root);
        } else {
            nom.setStyle(null);
        }
         
        
         Region r = new Region();
        r.setNom(nom.getText());
        r.setImage(path);
       RegionService ps= new RegionService();
        ps.ajouter(r);
          Parent root = FXMLLoader.load(getClass().getResource("AfficherRegion.fxml"));
               nom.getScene().setRoot(root);
    }

    @FXML
    private void opendest(ActionEvent event) {
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